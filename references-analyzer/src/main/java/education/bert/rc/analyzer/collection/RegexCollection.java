package education.bert.rc.analyzer.collection;

import education.bert.rc.analyzer.regexelements.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class RegexCollection {

    private final List<String> titleWords = new ArrayList<>();
    private final List<String> journalWords = new ArrayList<>();
    private final List<String> titles = new ArrayList<>();
    private final List<String> journals = new ArrayList<>();
    private final List<BaseElement> initials = new ArrayList<>();
    private final List<BaseGroup> initialGroups = new ArrayList<>();
    private final List<BaseElement> secondNames = new ArrayList<>();
    private final List<AuthorElement> authors = new ArrayList<>();
    private final List<AuthorGroup> authorGroups = new ArrayList<>();
    private final List<String> years = new ArrayList<>();
    private final List<BaseElement> volKeys = new ArrayList<>();
    private final List<BaseElement> volValues = new ArrayList<>();
    private final List<EntryElement> vols = new ArrayList<>();
    private final List<BaseElement> numKeys = new ArrayList<>();
    private final List<BaseElement> numValues = new ArrayList<>();
    private final List<EntryElement> nums = new ArrayList<>();
    private final List<BaseElement> otherKeys = new ArrayList<>();
    private final List<BaseElement> otherValues = new ArrayList<>();
    private final List<EntryElement> others = new ArrayList<>();
    private final List<BaseElement> pageKeys = new ArrayList<>();
    private final List<BaseElement> pageValues = new ArrayList<>();
    private final List<EntryElement> pages = new ArrayList<>();

    @Getter
    private final List<BibElement> articles = new ArrayList<>();
    @Getter
    private final List<BibElement> books = new ArrayList<>();

    private final String dash = "[-–]";

    public RegexCollection() {
        titleWords.add("\\b[A-ZА-Яa-zа-я]+\\b");

        journalWords.add("\\b[A-ZА-Яa-zа-я]+\\b");

        titleWords.forEach(word -> {
            titles.add("(?:" + word + "\\s*(?:" + dash + "\\s*)?)*" + word);
        });

        journalWords.forEach(word -> {
            journals.add("(?:" + word + "\\.?\\s*)*" + word + "\\.?");
        });

        initials.add(new BaseElement("\\b[A-ZА-Я]\\b"));

        initials.forEach(initial -> {
            initialGroups.add(new BaseGroup("(?:" + initial + "\\.?\\s*)?" + initial + "\\.?", initial));
        });

        secondNames.add(new BaseElement("\\b[A-ZА-Я][a-zа-я]+\\b"));

        initialGroups.forEach(initialGroup -> secondNames.forEach(secondName -> {
            authors.add(new AuthorElement(initialGroup + "\\s*" + secondName, initialGroup, secondName));
            authors.add(new AuthorElement(secondName + "\\s*" + initialGroup, initialGroup, secondName));
        }));

        authors.forEach(author -> {
            authorGroups.add(new AuthorGroup("(?:" + author + ",\\s*)*" + author + "(?:\\s+(?:and)?и?\\s+" +
                    author + ")?", author));
        });

        years.add("\\b[1-2]\\d{3}\\b");

        volKeys.add(new BaseElement("\\b[VТ]\\."));

        volValues.add(new BaseElement("\\b\\d+\\b"));

        volKeys.forEach(volKey -> volValues.forEach(volValue -> {
            vols.add(new EntryElement("(?:" + volKey + "\\s*)?" + volValue + "\\.?", volKey, volValue));
        }));

        numKeys.add(new BaseElement("№"));

        numValues.add(new BaseElement("\\b\\d+\\b"));

        numKeys.forEach(numKey -> numValues.forEach(numValue -> {
            nums.add(new EntryElement(numKey + "\\s*" + numValue + "\\.?", numKey, numValue));
        }));

        //TODO: otherKeys, otherValues, others

        pageKeys.add(new BaseElement("\\b[PС]\\."));

        pageValues.add(new BaseElement("\\b\\d+" + dash + "\\d+\\b"));

        pageKeys.forEach(pageKey -> pageValues.forEach(pageValue -> {
            pages.add(new EntryElement("(?:" + pageKey + "\\s*)?" + pageValue + "\\.?", pageKey, pageValue));
        }));

        journals.forEach(journal -> authorGroups.forEach(authorGroup -> years.forEach(year -> vols.forEach(vol ->
                pages.forEach(page -> titles.forEach(title -> {
                            articles.add(new BibElement(
                                    "(?<authorGroup>" + authorGroup + ")\\s+(?<year>" + year + ")(?:\\s+(?<title>"
                                            + title + "))?\\s+(?<journal>" + journal + ")\\s+(?<vol>" + vol +
                                            ")\\s+(?<page>" + page + ")",
                                    authorGroup,
                                    vol,
                                    null,
                                    null,
                                    page
                            ));
                            nums.forEach(num -> {
                                final BaseGroup initialGroup = authorGroup.getAuthor().getInitialGroup();
                                final BaseElement secondName = authorGroup.getAuthor().getSecondName();
                                articles.add(new BibElement(
                                        "(?:" + secondName + ",\\s*" + initialGroup + "(?:\\s*/)?\\s*)?(?<title>"
                                                + title + ")\\s*/\\s*(?<authorGroup>" + authorGroup +
                                                ")\\s*//\\s*(?<journal>" + journal + ")\\s*" + dash + "\\s*(?<year>" +
                                                year + ")\\.\\s*" + dash + "\\s*(?<vol>" + vol + ")\\s*(?:" + dash +
                                                "\\s*(?<num>" + num + ")\\s*)?" + dash + "\\s*(?<page>" + page + ")",
                                        authorGroup,
                                        vol,
                                        num,
                                        null,
                                        page
                                ));
                            });
                        })
                )))));

        System.out.println("[INFO] Article patterns: " + articles.size());
        System.out.println("[INFO] Book patterns: " + books.size());
    }
}
