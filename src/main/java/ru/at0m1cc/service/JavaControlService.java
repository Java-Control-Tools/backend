package ru.at0m1cc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.at0m1cc.db.UserPC;
import ru.at0m1cc.repository.UserPCRepository;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.List;

@Service
public class JavaControlService {
    private final Socket socket;
    /**
     * Поле репозитория
     * */
    private final UserPCRepository userPCRepository;
    /**
     * Конструктор с аннотацией Autowired для автоматического внедрения зависимостей
     * */
    @Autowired
    public JavaControlService(UserPCRepository userPCRepository) {
        this.userPCRepository = userPCRepository;
        socket = new Socket();
    }
    /**
     * Метод для проверки активности всех ПК
     * */
    public void checkActiveUsers(){
        List<UserPC> userPC = userPCRepository.findAll();
        for (UserPC user : userPC) {
            try {
                socket.connect(new InetSocketAddress(user.getIpAddress(), Integer.parseInt(user.getPort())), 1000);
                socket.getOutputStream().write("checkStatus".getBytes());
                socket.getOutputStream().close();
                socket.close();
                user.setStatus("active");
                userPCRepository.save(user);
            }
            catch (IOException e) {
                user.setStatus("inactive");
                userPCRepository.save(user);
            }
        }
    }

    /**
     * Вывод всех ПК пользователей, предварительно проверив их статус активности
     * */
    public List<UserPC> showUsers(){
        checkActiveUsers();
        return userPCRepository.findAll();
    }
    /**
     * Добавление ПК пользователя, предварительно проверив есть ли уже такой ПК в таблице
     * */
    public boolean addUser(String ipAddress, String port){
        if(userPCRepository.existsByIpAddressAndPort(ipAddress, port)){
            return false;
        }
        else {
            UserPC user = new UserPC();
            user.setIpAddress(ipAddress);
            user.setPort(port);
            user.setStatus("inactive");
            userPCRepository.save(user);
            return true;
        }
    }

    public boolean deleteUser(String ipAddress, String port){
        if(userPCRepository.existsByIpAddressAndPort(ipAddress, port)){
            UserPC user = userPCRepository.findByIpAddressAndPort(ipAddress, port);
            userPCRepository.delete(user);
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Метод для отправки команд на ПК пользователя
     * */
    public boolean sendCommandToUserPC(String ipAddress, String port, String command){
        try{
            socket.connect(new InetSocketAddress(ipAddress, Integer.parseInt(port)), 1000);
            socket.getOutputStream().write(command.getBytes());
            socket.getOutputStream().flush();
            socket.getOutputStream().close();
            socket.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

}
