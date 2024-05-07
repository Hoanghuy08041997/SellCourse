package model;

import java.util.ArrayList;
import java.util.List;

public class Transaction {
    private String transactionId;
    private List<Order> listOrders;
    private int uid;
    private String date;
    private double totalPrice;

    public Transaction() {
        listOrders = new ArrayList<>();
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public List<Order> getListOrders() {
        return listOrders;
    }

    public void setListOrders(List<Order> listOrders) {
        this.listOrders = listOrders;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "Transaction{" + "transactionId=" + transactionId + ", listOrders=" + listOrders + ", uid=" + uid + ", date=" + date + ", totalPrice=" + totalPrice + '}';
    }
    
}
