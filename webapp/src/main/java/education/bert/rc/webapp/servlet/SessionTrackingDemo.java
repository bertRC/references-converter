package education.bert.rc.webapp.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

@WebServlet(
        name = "SessionTrackingDemo",
        urlPatterns = {"/session"}
)
public class SessionTrackingDemo extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();

        String message;

        String sessionId = session.getId();
        Date sessionCreationDate = new Date(session.getCreationTime());
        Date lastSessionAccess = new Date(session.getLastAccessedTime());

        if (session.isNew()) {
            message = "Welcome to this page";
        } else {
            message = "Glad to see You again";
        }

        response.setContentType("text/html");

        PrintWriter writer = response.getWriter();

        String title = "Session Tracking Demo";
        String docType = "<!DOCTYPE html>";

        writer.println(docType + "<html>" +
                "<head>" +
                "<title>" + title +
                "</title>" +
                "</head>" +
                "<body>" +
                "<h1>Session Details</h1>" +
                "<h2>" + message + "</h2>" +
                "Session ID:" + sessionId +
                "<br/>" +
                "Created: " + sessionCreationDate +
                "<br/>" +
                "Last Accessed Date: " + lastSessionAccess +
                "</body>" +
                "</html>");
    }
}
