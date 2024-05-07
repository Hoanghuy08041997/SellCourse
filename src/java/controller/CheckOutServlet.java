package controller;

import dao.DAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Cart;
import model.Category;
import model.Course;

@WebServlet(name="CheckOutServlet", urlPatterns={"/checkout"})
public class CheckOutServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        DAO dao = DAO.getInstance();
        HttpSession session = request.getSession();
        Cart coursesToCheckout = new Cart();

        // Lấy danh sách ID được lưu trong session
        String[] selectedIds = (String[]) session.getAttribute("selectedIds");
        if (selectedIds != null) {
            Cart cart = getCartFromCookie(request);
            for (String id : selectedIds) {
                Course course = dao.getCourseById(id);
                if (course != null && cart.getCartList().containsKey(course)) {
                    coursesToCheckout.addCourseToCart(course, 1);
                }
                if (course != null && !cart.getCartList().containsKey(course)){
                    coursesToCheckout.addCourseToCart(course, 1);
                }
                
            }

            List<Category> listC = dao.getAllCategories();
            request.setAttribute("listC", listC);

            request.setAttribute("coursesToCheckout", coursesToCheckout.getCartList().keySet()); // Thay đổi ở đây

            request.getRequestDispatcher("checkout.jsp").forward(request, response);
        } else {
            response.sendRedirect("cart.jsp");
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

    @Override
    public String getServletInfo() {
        return "Short description";
    }

    private Cart getCartFromCookie(HttpServletRequest request) throws IOException {
        Cart cart = new Cart();
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("cartC")) {
                    try {
                        cart = DAO.getInstance().decode(cookie.getValue());
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(CheckOutServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
        return cart;
    }
}


