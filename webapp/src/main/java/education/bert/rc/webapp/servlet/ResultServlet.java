package education.bert.rc.webapp.servlet;

import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ResultServlet", urlPatterns = "/result")
public class ResultServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        final List<String> strings = (List<String>) this.getServletContext().getAttribute("strings");

        if (strings != null) {
            ServletOutputStream out = resp.getOutputStream();
            for (String string : strings) {
                out.println(string);
            }
            out.flush();
            out.close();
        }
    }
}
