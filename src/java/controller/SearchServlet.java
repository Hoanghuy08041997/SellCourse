
package controller;

import dao.DAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.Category;
import model.Course;
@WebServlet(name="SearchServlet", urlPatterns={"/search"})
public class SearchServlet extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String txtS = request.getParameter("txt");
        DAO dao = DAO.getInstance();
        List<Course> listP = dao.searchByName(txtS);
        List<Category> listC = dao.getAllCategories();
        Course lastP = dao.getLastestCourse();
        Course mostOrderP = dao.getMostOrderCourse();
        request.setAttribute("mostOrderP", mostOrderP);
        request.setAttribute("listP", listP);
        request.setAttribute("listC", listC);
        request.setAttribute("lastP", lastP);
        request.getRequestDispatcher("home.jsp").forward(request, response);
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
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
