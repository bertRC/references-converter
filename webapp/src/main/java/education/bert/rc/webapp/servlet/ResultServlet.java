package education.bert.rc.webapp.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "ResultServlet", urlPatterns = "/result")
public class ResultServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/plain;charset=UTF-8");
        final List<String> strings = (List<String>) this.getServletContext().getAttribute("strings");

        if (strings != null) {
            PrintWriter writer = resp.getWriter();
            strings.forEach(writer::println);
        }
    }
}
