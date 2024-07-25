package ru.at0m1cc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.at0m1cc.db.UserPC;
import ru.at0m1cc.dto.StatusCode;
import ru.at0m1cc.dto.StatusDTO;
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
    public ResponseEntity<StatusDTO> addUser(String ipAddress, String port){
        if(!userPCRepository.existsByIpAddressAndPort(ipAddress, port)){
            try{
                UserPC user = new UserPC();
                user.setIpAddress(ipAddress);
                user.setPort(port);
                user.setStatus("inactive");
                userPCRepository.save(user);
                return ResponseEntity.ok().body(new StatusDTO(StatusCode.OK));
            }
            catch (Exception e){
                return ResponseEntity.badRequest().body(new StatusDTO(StatusCode.ERROR));
            }
        }
        return ResponseEntity.badRequest().body(new StatusDTO(StatusCode.ERROR_ENTITY_ALREADY_EXISTS));
    }

    public ResponseEntity<StatusDTO> deleteUser(String ipAddress, String port){
        if(userPCRepository.existsByIpAddressAndPort(ipAddress, port)){
            UserPC user = userPCRepository.findByIpAddressAndPort(ipAddress, port);
            userPCRepository.delete(user);
            return ResponseEntity.ok().body(new StatusDTO(StatusCode.OK));
        }
        return ResponseEntity.status(404).body(new StatusDTO(StatusCode.ERROR_ENTITY_ALREADY_EXISTS));
    }

    /**
     * Метод для отправки команд на ПК пользователя
     */
    public ResponseEntity<StatusDTO> sendCommandToUserPC(String ipAddress, String port, String command){
        try{
            socket.connect(new InetSocketAddress(ipAddress, Integer.parseInt(port)), 1000);
            socket.getOutputStream().write(command.getBytes());
            socket.getOutputStream().flush();
            socket.getOutputStream().close();
            socket.close();
            return ResponseEntity.ok().body(new StatusDTO(StatusCode.OK));
        } catch (IOException e) {
            return ResponseEntity.status(503).body(new StatusDTO(StatusCode.ERROR));
        }
    }
}
