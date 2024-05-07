package controller;

import dao.DAO;
import model.Cart;
import model.Course;
import model.Order;
import model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/purchase")
public class PurchaseServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        
        String track = request.getParameter("selectedIds");
        String [] ids = track.split(",");
        if (ids == null || ids.length == 0) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "No courses selected for purchase.");
            return;
        }
        // Retrieve user and cart information from session and cookies
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("acc");
        if (user == null) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "User not logged in.");
            return;
        }


        Cart cart = new Cart();
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("cartC")) {
                    try {
                        cart = DAO.getInstance().decode(cookie.getValue());
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(PurchaseServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }

        // Process purchase
        List<Order> orders = new ArrayList<>();
        DAO dao = DAO.getInstance();
        for (String id : ids) {
            if (id != null) {
                Course course = dao.getCourseById(id);
                Order o = new Order(course, 1);
                orders.add(o);
                if (cart.getCartList().containsKey(course)){
                    cart.removeCourseToCart(course);
                }
            }
        }

        // Insert orders into database
        dao.insertTransaction(orders, user);

        // Update cart in cookies
        String cartData = dao.encode(cart);
        Cookie cartCookie = new Cookie("cartC", cartData);
        cartCookie.setMaxAge(60 * 60 * 24 * 7);
        response.addCookie(cartCookie);

        // Redirect to appropriate page
        response.sendRedirect("orders.jsp");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }
}
