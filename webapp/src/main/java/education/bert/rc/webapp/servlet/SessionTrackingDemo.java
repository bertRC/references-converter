package education.bert.rc.webapp.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet(
        name = "SessionTrackingDemo",
        urlPatterns = {"/session"}
)
public class SessionTrackingDemo extends HttpServlet {
}
