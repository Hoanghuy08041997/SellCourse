/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.DAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author huypd
 */
@WebServlet(name = "SignupServlet", urlPatterns = {"/sign-up"})
public class SignupServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        DAO dao = DAO.getInstance();
        String email = request.getParameter("email");
        boolean existed = dao.checkExistedEmail(email);
        if (!existed) { //email chưa tồn tại
            String pass = request.getParameter("password");
            String cf_pass = request.getParameter("confirm-password");
            if (!pass.equals(cf_pass)) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Password and cf-password is not equal.");
            } else {
                String fullName = request.getParameter("fullName");
                String address = request.getParameter("address");
                String phoneNumber = request.getParameter("phoneNumber");
                dao.signUp(fullName, address, phoneNumber, email, pass);
                response.sendRedirect("home");
            }
        }
        else { //email đã tồn tại hoặc syntax không hợp lệ
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Email is invalid or existed.");
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
