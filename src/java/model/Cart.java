/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import dao.DAO;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Cart implements Serializable{

    Map<Course, Integer> cartList;

    public Cart() {
        cartList = new HashMap<>();
    }

    public Map<Course, Integer> getCartList() {
        return cartList;
    }

    public void setCartList(Map<Course, Integer> cartList) {
        this.cartList = cartList;
    }
      
    
    public void addCourseToCart(Course c, int quantity) {
        this.cartList.put(c, quantity);
    }
    public void removeCourseToCart(Course c) {
        this.cartList.remove(c);
    }
    
    public static void main(String[] args) {
        DAO dao = DAO.getInstance();
        Course c1 = dao.getCourseById("DS101");
        Course c2 = dao.getCourseById("DS104");
        Course c3 = dao.getCourseById("DS104");
        Cart cart = new Cart();
        cart.addCourseToCart(c1, 1);
        cart.addCourseToCart(c2, 1);
        cart.addCourseToCart(c3, 4);
        cart.addCourseToCart(c1, 5);
        for (Course c : cart.getCartList().keySet()) {
            System.out.println(c);
        }
        for (int i : cart.getCartList().values()) {
            System.out.println(i);
        }
    }
}
