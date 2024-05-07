package controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/buyCheckbox")
public class BuyCheckboxServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String test = request.getParameter("selectedIds");
        String[] selectedIds = test.split(",");
        HttpSession session = request.getSession();
        
        if (selectedIds != null ) {
            session.setAttribute("selectedIds", selectedIds); 
            response.sendRedirect("checkout");
        } else {
            request.setAttribute("mes", "Have some error for checkout for you. Please contact with Fanpage.");
            response.sendRedirect("cart.jsp");
        }
    }
}
