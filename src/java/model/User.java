/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author huypd
 */
public class User {
    private int id;
    private String fullName;
    private String address;
    private String phoneNumber;
    private String email;
    private String passWord;
    private int isAdmin;

    public User() {
    }

    public User(int id, String fullName, String address, String phoneNumber, String email, String passWord, int isAdmin) {
        this.id = id;
        this.fullName = fullName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.passWord = passWord;
        this.isAdmin = isAdmin;
    }

    public int getId() {
        return id;
    }   
    
    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    
    
    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public void setIsAdmin(int isAdmin) {
        this.isAdmin = isAdmin;
    }

    public int getIsAdmin() {
        return isAdmin;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", fullName=" + fullName + ", address=" + address + ", phoneNumber=" + phoneNumber + ", email=" + email + ", passWord=" + passWord + ", isAdmin=" + isAdmin + '}';
    }
    
}
