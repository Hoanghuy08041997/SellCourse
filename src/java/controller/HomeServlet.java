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

@WebServlet(name = "HomeServlet", urlPatterns = {"/home"})
public class HomeServlet extends HttpServlet {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        DAO dao = DAO.getInstance();
        
        Cart cart = new Cart();
        Cookie[] arr = request.getCookies();
        if (arr != null) {
            for (Cookie c : arr) {
                if (c.getName().equals("cartC")) {
                    try {
                        cart = dao.decode(c.getValue());
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(CartServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
        
        List<Course> listP = dao.getAllCourses();
        List<Category> listC = dao.getAllCategories();
        Course lastP = dao.getLastestCourse();
        Course mostOrderP = dao.getMostOrderCourse();
        request.setAttribute("mostOrderP", mostOrderP);
        request.setAttribute("listP", listP);
        request.setAttribute("listC", listC);
        request.setAttribute("lastP", lastP);
       
        Cookie sizeC = new Cookie("sizeC", String.valueOf(cart.getCartList().size()));
        sizeC.setMaxAge(60 * 60 * 24 * 7);
        response.addCookie(sizeC);

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
}
