
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
@WebServlet(name = "RemoveFromCartServlet", urlPatterns = {"/remove-from-cart"})
public class RemoveFromCartServlet extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        DAO dao = DAO.getInstance();
        Cart cart = new Cart();
        String id = request.getParameter("id");
        Course course = dao.getCourseById(id);
        Cookie[] arr = request.getCookies();
        for (Cookie c : arr) {
            if (c.getName().equals("cartC"))
                try {
                cart = dao.decode(c.getValue());
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(RemoveFromCartServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (id != null) {
            cart.removeCourseToCart(course);
        }
        Cookie cartC = new Cookie("cartC", dao.encode(cart));
        cartC.setMaxAge(60 * 60 * 24 * 7);
        response.addCookie(cartC);

        Cookie sizeC = new Cookie("sizeC", String.valueOf(cart.getCartList().size()));
        sizeC.setMaxAge(60 * 60 * 24 * 7);
        response.addCookie(sizeC);

        response.sendRedirect("cart");
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
