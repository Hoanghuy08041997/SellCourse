
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Course;
import dao.DAO;
import jakarta.servlet.annotation.WebServlet;
import java.util.List;
import java.util.Random;
@WebServlet(name="AddProductServlet", urlPatterns={"/add"})
public class AddProductServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String name = request.getParameter("name");
        String image = request.getParameter("image");
        String description = request.getParameter("description");
        double price = Double.parseDouble(request.getParameter("price"));
        int duration_month = Integer.parseInt(request.getParameter("duration"));
        int cid = Integer.parseInt(request.getParameter("category"));
        String publicDate = request.getParameter("publicDate")+":00.000";
        String id = randomId(cid);
        double discount= (double) Double.parseDouble(request.getParameter("discount"))/100;
        try{
            DAO dao = DAO.getInstance();
            Course course = new Course(id, name, image, description, price, duration_month, cid, publicDate, discount);
            dao.addCourse(course);
        } catch(Exception e){
            System.out.println(e);
        }
        response.sendRedirect("manager");
    } 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String name = request.getParameter("name");
        String image = request.getParameter("image");
        String description = request.getParameter("description");
        double price = Double.parseDouble(request.getParameter("price"));
        int duration_month = 1;
        int cid = Integer.parseInt(request.getParameter("category"));
        String publicDate = request.getParameter("publicDate")+":00.000";
        String id = randomId(cid);
        double discount= (double) Double.parseDouble(request.getParameter("discount"))/100;
        try{
            DAO dao = DAO.getInstance();
            Course course = new Course(id, name, image, description, price, duration_month, cid, publicDate, discount);
            dao.addCourse(course);
        } catch(Exception e){
            System.out.println(e);
        }
        response.sendRedirect("manager");
    }
    
    public String randomId(int cid) {
        Random random = new Random();       
        String id = "";
        switch(cid){
            case 1:{
                id += "WD";
                break;
            }
            case 2:{
                id += "DS";
                break;
            }
            case 3:{
                id += "MD";
                break;
            }
            case 4:{
                id += "ML";
                break;
            }
            case 5:{
                id += "GD";
                break;
            }            
        }
        for (int i = 0; i < 3; i++) {            
            id += random.nextInt(10);
        }
        while(!checkId(id)){
            randomId(cid);
        }
        return id;
    }
    
    public boolean checkId(String id){
        DAO dao = DAO.getInstance();
        List<Course> list = dao.getAllCourses();
        for(Course course : list){
            if(course.getId().equalsIgnoreCase(id)){
                return false;
            }
        }
        return true;
    }

}
