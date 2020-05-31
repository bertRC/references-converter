package education.bert.rc.webapp.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "ResultServletDemo", urlPatterns = "/resultDemo")
public class ResultServletDemo extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/plain;charset=UTF-8");
        HttpSession session = req.getSession();
//        final List<String> strings = (List<String>) this.getServletContext().getAttribute("strings");
        final List<String> strings = (List<String>) session.getAttribute("strings");
//        if (strings != null) {
        PrintWriter printWriter = resp.getWriter();
        strings.forEach(printWriter::println);
//        }
    }
}
