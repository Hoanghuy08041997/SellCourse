package controller;

import dao.DAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Cart;
import model.Course;

@WebServlet("/deleteSelected")
public class DeleteCheckboxServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String test = request.getParameter("selectedIds");
        String[] selectedIds = test.split(","); //danh sách các id đã tick

        if (selectedIds != null && selectedIds.length > 0) {
            DAO dao = DAO.getInstance();
            Cookie[] cookies = request.getCookies();
            Cart cart = new Cart();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("cartC")) {
                        try {
                            cart = dao.decode(cookie.getValue());
                        } catch (ClassNotFoundException ex) {
                            Logger.getLogger(DeleteCheckboxServlet.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        break;
                    }
                }
            }

            if (cart != null) {
                for (String id : selectedIds) {
                    Course course = dao.getCourseById(id);
                    cart.removeCourseToCart(course);
                }
                Cookie cartCookie = new Cookie("cartC", dao.encode(cart));
                cartCookie.setMaxAge(60 * 60 * 24 * 7);
                response.addCookie(cartCookie);
            }
        }
        response.sendRedirect("cart.jsp");
    }
}
