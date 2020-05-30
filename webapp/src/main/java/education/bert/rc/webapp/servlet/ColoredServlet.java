package education.bert.rc.webapp.servlet;

import education.bert.rc.analyzer.service.Analyzer;
import education.bert.rc.utils.colors.css.CssColors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ColoredServlet", urlPatterns = "/colored")
public class ColoredServlet extends HttpServlet {

    private String coloredText;

    @Override
    public void init() {
        final Analyzer analyzer = new Analyzer();
        String text = "1. Белев, Д.О. / Некоторые анализы средневековой системы дальше не стал / Д. О. Белев, И.П. Сидоров, Ф.Р.  Мобо, М.А.Венхофф // Био. Мат. - 1998. - Т. 14. - С. 3397-3503. [http...]";
        coloredText = CssColors.colorize(analyzer.analyze(text)) + "<br>"
                + CssColors.colorize("V. 15.", CssColors.VOLUME_COLOR) + " - "
                + CssColors.colorize("N. 12.", CssColors.NUMBER_COLOR) + " - "
                + CssColors.colorize("Issue 2.", CssColors.OTHER_COLOR);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("coloredText", coloredText);
        req.getRequestDispatcher("test-colors.jsp").forward(req, resp);
    }
}
