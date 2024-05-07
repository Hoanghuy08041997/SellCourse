/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author huypd
 */
//<!--Date Name Category Quantity Price-->
public class Order{
    private Course course;
    private int quantity;
    private String activationCode;
    private String endDate;
    public Order() {
    }

    public Order(Course course, int quantity) {
        this.course = course;
        this.quantity = quantity;
    }
    

    public Order(Course course, int quantity, String activationCode, String endDate) {
        this.course = course;
        this.quantity = quantity;
        this.activationCode = activationCode;
        this.endDate = endDate;
    }
    
    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getActivationCode() {
        return activationCode;
    }

    public void setActivationCode(String activationCode) {
        this.activationCode = activationCode;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
    
    
}
