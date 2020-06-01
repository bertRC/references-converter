package education.bert.rc.webapp.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "ResultServlet", urlPatterns = {"/result", "/copyResult"})
public class ResultServlet extends HttpServlet {

    private String resultsInLine;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        switch (req.getRequestURI()) {
            case "/result":
                HttpSession session = req.getSession();
                resultsInLine = (String) session.getAttribute("resultsInLine");
                req.getRequestDispatcher("result.jsp").forward(req, resp);
                break;
            case "/copyResult":
                if (resultsInLine != null) {
                    resp.getWriter().write(resultsInLine);
                }
        }
    }
}
