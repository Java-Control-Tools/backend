package ru.at0m1cc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.at0m1cc.db.UserPC;
import ru.at0m1cc.dto.StatusCode;
import ru.at0m1cc.dto.StatusDTO;
import ru.at0m1cc.repository.UserPCRepository;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.List;

@Service
public class JavaControlService {
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
    }
    /**
     * Метод для проверки активности всех ПК
     * */
    public void checkActiveUsers(){
        List<UserPC> userPC = userPCRepository.findAll(); //Поиск всех ПК в БД
        for (UserPC user : userPC) { // С помощью цикла идет проверка каждого ПК на активность
            try {
                Socket socket = new Socket();
                socket.connect(new InetSocketAddress(user.getIpAddress(), Integer.parseInt(user.getPort())), 200);
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
        return userPCRepository.findAll(); // Поиск всех ПК в БД и отправка в формате листа на view
    }
    /**
     * Добавление ПК пользователя, предварительно проверив есть ли уже такой ПК в таблице
     * */
    public ResponseEntity<StatusDTO> addUser(String ipAddress, String port){
        if(!userPCRepository.existsByIpAddressAndPort(ipAddress, port)){ // Проверка на то, нет ли такой же записи в бд
            try{
                UserPC user = new UserPC();
                user.setIpAddress(ipAddress);
                user.setPort(port);
                user.setStatus("inactive");
                userPCRepository.save(user);
                return ResponseEntity.ok().body(new StatusDTO(StatusCode.OK));
            }
            catch (Exception e){
                return ResponseEntity.badRequest().body(new StatusDTO(StatusCode.ERROR)); // Если что-либо пошло не так
            }
        }
        return ResponseEntity.badRequest().body(new StatusDTO(StatusCode.ERROR_ENTITY_ALREADY_EXISTS));
    }
    /**
     * Метод для удаления ПК пользователя
     */
    public ResponseEntity<StatusDTO> deleteUser(String ipAddress, String port){
        if(userPCRepository.existsByIpAddressAndPort(ipAddress, port)){ // Проверка на то есть ли такая запись вообще
            UserPC user = userPCRepository.findByIpAddressAndPort(ipAddress, port);
            userPCRepository.delete(user);
            return ResponseEntity.ok().body(new StatusDTO(StatusCode.OK));
        }
        return ResponseEntity.status(404).body(new StatusDTO(StatusCode.ERROR_ENTITY_ALREADY_EXISTS));
    }
    /**
     * Метод для обновления ПК пользователя
     */
    public ResponseEntity<StatusDTO> updateUser(String oldIpAddress, String oldPort, String newIpAddress, String newPort){
        if(userPCRepository.existsByIpAddressAndPort(oldIpAddress, oldPort) && !userPCRepository.existsByIpAddressAndPort(newIpAddress, newPort)){
            // Тут проверка на то есть ли запись по старым данным и одновременно проверка нет ли такой же записи по новым данным
            // Сделано, для того чтобы избежать повторения записей
            UserPC user = userPCRepository.findByIpAddressAndPort(oldIpAddress, oldPort);
            user.setIpAddress(newIpAddress);
            user.setPort(newPort);
            userPCRepository.saveAndFlush(user);
            return ResponseEntity.ok().body(new StatusDTO(StatusCode.OK));
        }
        return ResponseEntity.status(503).body(new StatusDTO(StatusCode.ERROR));
    }

    /**
     * Метод для отправки команд на ПК пользователя
     */
    public ResponseEntity<StatusDTO> sendCommandToUserPC(String ipAddress, String port, String command){
        try{
            if(command.equals("screen")){ //Если команда скриншота, то мы перенаправляем в отдельный метод для скриншотинга
                try {
                    sendCommandScreen(ipAddress, port);
                    return ResponseEntity.ok().body(new StatusDTO(StatusCode.OK));
                }
                catch (IOException e){
                    return ResponseEntity.badRequest().body(new StatusDTO(StatusCode.ERROR)); //Если что-либо пошло не по плану
                }

            }
            Socket socket = new Socket();
            socket.connect(new InetSocketAddress(ipAddress, Integer.parseInt(port)), 200);
            socket.getOutputStream().write((command + "\n").getBytes());
            socket.getOutputStream().flush();
            socket.getOutputStream().close();
            socket.close();
            return ResponseEntity.ok().body(new StatusDTO(StatusCode.OK));
        } catch (IOException e) {
            return ResponseEntity.status(503).body(new StatusDTO(StatusCode.ERROR)); // Если что-либо пошло не так
        }
    }
    /**
     * Метод для отправки команды screen и получения файла изображения
     */
    public void sendCommandScreen(String ipAddress, String port) throws IOException {
        Socket socket = new Socket();
        socket.connect(new InetSocketAddress(ipAddress, Integer.parseInt(port)), 200);
        BufferedInputStream bis = new BufferedInputStream(socket.getInputStream());
        BufferedOutputStream bos = new BufferedOutputStream(socket.getOutputStream());
        bos.write("screen\n".getBytes());
        bos.flush();
        FileOutputStream fos = new FileOutputStream("screen.png", false); // Создаём пустышку
        byte[] byteArray = new byte[8192];
        int bytesRead;
        while ((bytesRead = bis.read(byteArray)) != -1) { //Читаем и записываем в файл байты
            fos.write(byteArray, 0, bytesRead);
        }
        bis.close();
        bos.close();
        socket.close();
    }
}
