package com.sjlw.bean;

public class Reader {
    private long readerId;
    private String username;
    private String password;

    public long getreaderId() {
        return readerId;
    }

    public void setreaderId(long readerId) {
        this.readerId = readerId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Reader{" +
                "readerId=" + readerId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
