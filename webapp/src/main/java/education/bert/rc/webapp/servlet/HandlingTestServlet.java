package education.bert.rc.webapp.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "HandlingTestServlet", urlPatterns = {"/handling", "/scriptHandler"})
public class HandlingTestServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        switch (req.getRequestURI()) {
            case "/handling":
                req.setAttribute("someText", "Some Text");
                req.getRequestDispatcher("handling-test.jsp").forward(req, resp);
                break;
            case "/scriptHandler":
                final String scriptToServlet = req.getParameter("scriptToServlet");
                String result = scriptToServlet + (int) (100 * Math.random());
                resp.getWriter().write(result);
        }
    }
}
