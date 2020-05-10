package education.bert.rc.analyzer.service;

import education.bert.rc.analyzer.collection.RegexCollection;
import education.bert.rc.analyzer.regexelements.BaseElement;
import education.bert.rc.analyzer.regexelements.BibElement;
import education.bert.rc.analyzer.repository.Bibliography;
import education.bert.rc.analyzer.repository.RawBibliography;

import java.util.List;
import java.util.stream.Collectors;

public class Analyzer {

    private final RegexCollection collection = new RegexCollection();

    public Analyzer() {
    }

    public String getLanguage(String text) {
        final BaseElement latin = new BaseElement("[A-Za-z]");
        final BaseElement cyrillic = new BaseElement("[А-Яа-я]");
        if (latin.find(text).size() > cyrillic.find(text).size()) {
            return "latin";
        } else {
            return "cyrillic";
        }
    }

    public Bibliography analyze(String text) {
        final String language = getLanguage(text);
        int coverage = 0;
        Bibliography result = new Bibliography(text);
        RawBibliography rawBibliography;
        for (BibElement bibElement : collection.getArticles()) {
            rawBibliography = bibElement.parse(text);
            if (rawBibliography.getCoverage() > coverage) {
                coverage = rawBibliography.getCoverage();
                result = new Bibliography(rawBibliography, false, language);
            }
        }
        for (BibElement bibElement : collection.getBooks()) {
            rawBibliography = bibElement.parse(text);
            if (rawBibliography.getCoverage() > coverage) {
                coverage = rawBibliography.getCoverage();
                result = new Bibliography(rawBibliography, true, language);
            }
        }
        return result;
    }

    public List<Bibliography> analyze(List<String> lines) {
//        return lines.stream().map(this::analyze).collect(Collectors.toList());
        return lines.parallelStream().map(this::analyze).collect(Collectors.toList());
    }
}
