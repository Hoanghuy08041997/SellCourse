package i18n;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/LanguageManager")
public class LanguageManager extends HttpServlet {
    private static final String LANGUAGE_ATTRIBUTE = "language";

    public LanguageManager() {
    }
    

    public void setLanguage(HttpServletRequest request, String language) {
        HttpSession session = request.getSession();
        session.setAttribute(LANGUAGE_ATTRIBUTE, language);
    }

    public String getLanguage(HttpServletRequest request) {
        HttpSession session = request.getSession();
        return (String) session.getAttribute(LANGUAGE_ATTRIBUTE);
    }   
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String language = request.getParameter("language");
        String result = "Language updated to " + language;
        setLanguage(request, language);
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(result);
    }
}
