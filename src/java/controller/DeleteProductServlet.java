package controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import dao.DAO;
import jakarta.servlet.annotation.WebServlet;
@WebServlet(name="DeleteProductServlet", urlPatterns={"/delete"})
public class DeleteProductServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {   
        DAO dao = DAO.getInstance();
        try{
            String id = request.getParameter("pid");
            dao.deleteCourseById(id);
            response.sendRedirect("manager");
        } catch(Exception e){
            System.out.println(e);
        }
    } 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DAO dao = DAO.getInstance();
        String test = request.getParameter("selectedIds");
        String[] selectedIds = test.split(",");
        try{
            for (String selectedOption : selectedIds) {
                dao.deleteCourseById(selectedOption);
            }
            response.sendRedirect("manager");
        } catch(Exception e){
            System.out.println(e);
        }
    }
}
