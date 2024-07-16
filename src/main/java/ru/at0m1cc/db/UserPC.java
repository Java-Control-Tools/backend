package ru.at0m1cc.db;

import jakarta.persistence.*;

@Entity
@Table(name = "USER_PC")
public class UserPC {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private long id;
    @Column(name = "IP_ADDRESS")
    private String ipAddress;
    @Column(name = "PORT")
    private String port;
    @Column(name = "STATUS")
    private String status;
    public UserPC() {}
    public UserPC(String ipAddress, String port, String status) {
        this.ipAddress = ipAddress;
        this.port = port;
        this.status = status;
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
