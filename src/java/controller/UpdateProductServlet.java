package controller;

import dao.DAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Course;
@WebServlet(name="UpdateProductServlet", urlPatterns={"/update"})
public class UpdateProductServlet extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet UpdateProductServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateProductServlet at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
    } 

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String image = request.getParameter("image");
        String description = request.getParameter("description");
        double price = Double.parseDouble(request.getParameter("price"));
        int duration_month = Integer.parseInt(request.getParameter("duration"));
        int cid = Integer.parseInt(request.getParameter("category"));
        String publicDate = request.getParameter("publicDate")+":00.000";
        double discount= (double) Double.parseDouble(request.getParameter("discount"))/100;
        try{
            DAO dao = DAO.getInstance();
            Course course = new Course(id, name, image, description, price, duration_month, cid, publicDate, discount);
            dao.updateCourse(course);
        } catch(Exception e){
            System.out.println(e);
        }
        response.sendRedirect("manager");
    }
}
