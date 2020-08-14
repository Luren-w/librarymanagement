package com.sjlw.bean;

public class Admin {

    private long adminId;
    private String password;
    private String username;

    public Admin() {
    }

    public Admin(long adminId, String password, String username) {
        this.adminId = adminId;
        this.password = password;
        this.username = username;
    }

    public long getAdminId() {
        return adminId;
    }

    public void setAdminId(long adminId) {
        this.adminId = adminId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "adminId=" + adminId +
                ", password='" + password + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
