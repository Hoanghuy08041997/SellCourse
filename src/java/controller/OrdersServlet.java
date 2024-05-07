package controller;

import dao.DAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import model.Category;
import model.Transaction;
import model.User;

@WebServlet(name = "ListOrderServlet", urlPatterns = {"/orders"})
public class OrdersServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        DAO dao = DAO.getInstance();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("acc");
        List<Transaction> listTransactions = dao.getUserOrder(user);
        List<Category> listC = dao.getAllCategories();
        request.setAttribute("listC", listC);
        request.setAttribute("transactionList", listTransactions);
        request.getRequestDispatcher("orders.jsp").forward(request, response);
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
