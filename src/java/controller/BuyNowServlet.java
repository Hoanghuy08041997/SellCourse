package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/buynow")
public class BuyNowServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String courseId = request.getParameter("id");
        HttpSession session = request.getSession();
        if (courseId != null) {
            String[] selectedIds = { courseId };
            session.setAttribute("selectedIds", selectedIds);
            response.sendRedirect("checkout"); 
        } else {
            response.getWriter().println("No course ID provided.");
        }
    }
}

