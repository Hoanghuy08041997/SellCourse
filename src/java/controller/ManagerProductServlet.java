package controller;

import dao.DAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Category;
import model.Course;
@WebServlet(name="ManagerProductServlet", urlPatterns={"/manager"})
public class ManagerProductServlet extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String indexS = request.getParameter("index");
        if (indexS == null)
            indexS = "1";
        int index = Integer.parseInt(indexS);
        DAO dao = DAO.getInstance();
        int count = dao.getTotalCourse(); //đếm số khóa học đang có
        int endPage = count/4;
        if (count%4 != 0)
            endPage++;
        //in ra các khóa của trang "index"
        List<Course> listP = dao.pagingCourses(index);
        request.setAttribute("listP", listP);
        request.setAttribute("endPage", endPage);
        request.setAttribute("count", count);
        request.setAttribute("index", index);
        request.getRequestDispatcher("managerProduct.jsp").forward(request, response);
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
