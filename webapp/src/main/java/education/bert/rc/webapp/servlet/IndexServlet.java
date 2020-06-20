package education.bert.rc.webapp.servlet;

import education.bert.rc.analyzer.service.Analyzer;
import education.bert.rc.generator.collection.TemplateCollection;
import education.bert.rc.utils.colors.css.CssColors;
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

@WebServlet(name = "IndexServlet", urlPatterns = "")
public class IndexServlet extends HttpServlet {

    private Analyzer analyzer;
    private TemplateCollection templateCollection;

    @Override
    public void init() {
        analyzer = (Analyzer) this.getServletContext().getAttribute("analyzer");
        templateCollection = (TemplateCollection) this.getServletContext()
                .getAttribute("templateCollection");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if ("convert".equals(req.getParameter("action"))) {

            HttpSession session = req.getSession();

            final String selectTemplate = req.getParameter("selectTemplate");
            final int templateIndex = Integer.parseInt(selectTemplate);
            final String inputText = req.getParameter("inputText");
            session.setAttribute("templateSelected", selectTemplate);
            session.setAttribute("plainInputText", inputText);

            if (!inputText.isEmpty()) {
                final String lineSeparator = Separator.getLineSeparator(inputText);
                final List<String> inputStrings = Separator.separate(inputText);
                final List<Bibliography> bibliographies = analyzer.analyze(inputStrings);
                final List<String> coloredStrings = CssColors.colorize(bibliographies);
                final List<String> results = templateCollection.getAll().get(templateIndex).generate(bibliographies);
                final String resultsInLine = Separator.join(results, lineSeparator);

                session.setAttribute("lineSeparator", lineSeparator);
                session.setAttribute("bibliographies", bibliographies);
                session.setAttribute("coloredStrings", coloredStrings);
                session.setAttribute("results", results);
                session.setAttribute("resultsInLine", resultsInLine);
            }

            resp.sendRedirect("/result");
        }
    }
}
