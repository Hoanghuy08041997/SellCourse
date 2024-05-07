package controller;

import dao.DAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.Category;
import model.Course;

@WebServlet(name="DetailServlet", urlPatterns={"/detail"})
public class DetailServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String id = request.getParameter("id");
        DAO dao = DAO.getInstance();
        Course c = dao.getCourseById(id);
        List<Category> listC = dao.getAllCategories();
        Course lastP = dao.getLastestCourse();
        Course mostOrderP = dao.getMostOrderCourse();
        request.setAttribute("mostOrderP", mostOrderP);
        request.setAttribute("listC", listC);
        request.setAttribute("lastP", lastP);
        request.setAttribute("c", c);
        request.getRequestDispatcher("detail.jsp").forward(request, response);
    } 

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    } 

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }

}
