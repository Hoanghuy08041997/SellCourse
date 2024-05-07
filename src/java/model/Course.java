/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import dao.DAO;
import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author huypd
 */
public class Course implements Serializable {

    private String id;
    private String name;
    private String image;
    private String description;
    private double price;
    private int duration_month;
    private int cid;
    private String publicDate;
    private double discount;

    public Course() {
    }

    public Course(String id, String name, String image, String description, double price, int duration_month, int cid, String publicDate, double discount) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.description = description;
        this.price = price;
        this.duration_month = duration_month;
        this.cid = cid;
        this.publicDate = publicDate;
        this.discount = discount;
    }

    

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getDuration_month() {
        return duration_month;
    }

    public void setDuration_month(int duration_month) {
        this.duration_month = duration_month;
    }

    

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getPublicDate() {
        return publicDate;
    }

    public void setPublicDate(String publicDate) {
        this.publicDate = publicDate;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public String getCname() {
        DAO dao = DAO.getInstance();
        return dao.getCategoryByCid(cid);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.id);
        hash = 79 * hash + Objects.hashCode(this.name);
        hash = 79 * hash + Objects.hashCode(this.image);
        hash = 79 * hash + Objects.hashCode(this.description);
        hash = 79 * hash + (int) (Double.doubleToLongBits(this.price) ^ (Double.doubleToLongBits(this.price) >>> 32));
        hash = 79 * hash + (int) (Double.doubleToLongBits(this.discount) ^ (Double.doubleToLongBits(this.discount) >>> 32));
        hash = 79 * hash + this.duration_month;
        hash = 79 * hash + this.cid;
        hash = 79 * hash + Objects.hashCode(this.publicDate);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Course other = (Course) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (Double.doubleToLongBits(this.price) != Double.doubleToLongBits(other.price)) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.image, other.image)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (this.duration_month != other.duration_month) {
            return false;
        }
        if (!Objects.equals(this.publicDate, other.publicDate)) {
            return false;
        }
        if (Double.doubleToLongBits(this.discount) != Double.doubleToLongBits(other.discount)) {
            return false;
        }
        return (this.cid == other.cid);
    }

    @Override
    public String toString() {
        return "Course{" + "id=" + id + ", name=" + name + ", image=" + image + ", description=" + description + ", price=" + price + ", duration_month=" + duration_month + ", cid=" + cid + ", publicDate=" + publicDate + ", discount=" + discount + '}';
    }

    

    public static void main(String[] args) {
        Course c = new Course();
        c.setCid(1);
        //System.out.println(c.getCname());
    }
}
