package education.bert.rc.webapp.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@WebServlet(name = "IndexServlet", urlPatterns = "")
public class IndexServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        final String text = req.getParameter("inputText");
//        System.out.println(text);
        final List<String> strings = Arrays.asList(text.split("(\r\n)|(\n\r)|[\r\n]"));
//        this.getServletContext().setAttribute("strings", strings);
        session.setAttribute("strings", strings);
        resp.sendRedirect("/resultDemo");
    }
}
