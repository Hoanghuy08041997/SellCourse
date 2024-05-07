package controller;

import dao.DAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Order;
import model.User;

@WebServlet(name="ActiveCourseServlet", urlPatterns={"/activeCourse"})
public class ActiveCourseServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        DAO dao = DAO.getInstance();
        
        String id = request.getParameter("id");
        String activationCode = request.getParameter("key");
        
        if (activationCode != null && !activationCode.isEmpty()) {
            Order o = dao.getOrderByActivationCode(activationCode);
            if (o != null) {
                dao.updateExpirationDay(activationCode);
                request.setAttribute("mes", "Active course successfully.");
            } else {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "This code is invalid. Please recheck your order.");
            }
        } else {
            // Trả về lỗi 400 Bad Request
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Activation key is missing.");
        }
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
