
package controller;

import dao.DAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Cart;
import model.Category;
import model.Course;
@WebServlet(name = "AddToCartServlet", urlPatterns = {"/add-to-cart"})
public class AddToCartServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String idS = request.getParameter("id");
        String quantityS = request.getParameter("quantity");
        int quantity = Integer.parseInt(quantityS);
        Cart cart = new Cart();
        DAO dao = DAO.getInstance();
        Cookie[] arr = request.getCookies();
        if (arr != null) {
            for (Cookie c : arr) {
                if (c.getName().equals("cartC")) {
                    try {
                        cart = dao.decode(c.getValue()); //mã hóa String thành List<Cart>
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(AddToCartServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
        if (cart == null) {
            cart = new Cart(); //lỡ mã hóa xong thì curList bị null nên khởi tạo lại
        }

        Course course = dao.getCourseById(idS);
        if (cart.getCartList().containsKey(course)) {
            List<Course> listP = dao.getAllCourses();
            List<Category> listC = dao.getAllCategories();
            Course lastP = dao.getLastestCourse();
            Course mostOrderP = dao.getMostOrderCourse();
            request.setAttribute("mostOrderP", mostOrderP);
            request.setAttribute("listP", listP);
            request.setAttribute("listC", listC);
            request.setAttribute("lastP", lastP);
            request.setAttribute("tagCourseId", idS);
            request.setAttribute("mes", "Item Already in Cart");
            request.getRequestDispatcher("home.jsp").forward(request, response);
        } else {
            cart.addCourseToCart(course, quantity);
            String txt = dao.encode(cart);
            Cookie cartC = new Cookie("cartC", txt); //tạo lại cartC
            cartC.setMaxAge(60 * 60 * 24 * 7);
            response.addCookie(cartC);

            Cookie sizeC = new Cookie("sizeC", String.valueOf(cart.getCartList().size()));
            sizeC.setMaxAge(60 * 60 * 24 * 7);
            response.addCookie(sizeC);

            response.sendRedirect("home");
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
