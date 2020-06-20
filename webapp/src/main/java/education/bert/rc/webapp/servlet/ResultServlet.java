package education.bert.rc.webapp.servlet;

import education.bert.rc.generator.collection.TemplateCollection;
import education.bert.rc.utils.misc.Separator;
import education.bert.rc.utils.repository.Bibliography;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ResultServlet", urlPatterns = {"/result", "/copyResult"})
public class ResultServlet extends HttpServlet {

    private TemplateCollection templateCollection;

    @Override
    public void init() {
        templateCollection = (TemplateCollection) this.getServletContext()
                .getAttribute("templateCollection");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        switch (req.getRequestURI()) {
            case "/result":
                req.getRequestDispatcher("result.jsp").forward(req, resp);
                break;
            case "/copyResult":
                final String resultsInLine = (String) req.getSession().getAttribute("resultsInLine");
                resp.getWriter().write(resultsInLine == null ? "" : resultsInLine);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if ("convert".equals(req.getParameter("action"))) {

            HttpSession session = req.getSession();

            final String selectTemplate = req.getParameter("selectTemplate");
            final int templateIndex = Integer.parseInt(selectTemplate);
            session.setAttribute("templateSelected", selectTemplate);

            final String lineSeparator = (String) session.getAttribute("lineSeparator");
            final List<Bibliography> bibliographies = (List<Bibliography>) session.getAttribute("bibliographies");
            if (bibliographies != null) {
                final List<String> results = templateCollection.getAll().get(templateIndex).generate(bibliographies);
                final String resultsInLine = Separator.join(results, lineSeparator);

                session.setAttribute("results", results);
                session.setAttribute("resultsInLine", resultsInLine);
            }

            resp.sendRedirect("/result");
        }
    }
}
