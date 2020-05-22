package education.bert.rc.generator.collection;

import education.bert.rc.generator.templates.BibTeXTemplate;
import education.bert.rc.generator.templates.StandardTemplate;
import education.bert.rc.generator.templates.Template;

import java.util.ArrayList;
import java.util.List;

public class TemplateCollection {

    private final List<Template> templates = new ArrayList<>();

    public TemplateCollection() {
        templates.add(new BibTeXTemplate("BibTeX"));

        templates.add(new StandardTemplate(
                "ГОСТ 7.0.5. 2008",
                "<firstAuthor=\"Ivanov, I.I.\"> / <title> / <authors=\"I.I. Ivanov\"> // <journal>. – <year>.<vol=\" – V. ?.\"><num=\" – N. ?.\"><pages=\" – P. ?\">.",
                "<firstAuthor=\"Ivanov, I.I.\"> / <title> / <authors=\"I.I. Ivanov\"> // <journal>. – <year>.<vol=\" – Т. ?.\"><num=\" – №. ?.\"><pages=\" – С. ?\">.",
                "<firstAuthor=\"Ivanov, I.I.\"> / <title> / <authors=\"I.I. Ivanov\">. – <journal>, <year>.<vol=\" V. ?.\"><num=\" – N. ?.\"><pages=\" – ? p.\">.",
                "<firstAuthor=\"Ivanov, I.I.\"> / <title> / <authors=\"I.I. Ivanov\">. – <journal>, <year>.<vol=\" Т. ?.\"><num=\" – №. ?.\"><pages=\" – ? с.\">."
        ));
    }

    public List<Template> getAll() {
        return templates;
    }
}
