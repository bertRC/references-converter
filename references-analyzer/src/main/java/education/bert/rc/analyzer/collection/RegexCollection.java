package education.bert.rc.analyzer.collection;

import education.bert.rc.analyzer.regexelements.*;

import java.util.ArrayList;
import java.util.List;

public class RegexCollection {

    private final List<BaseElement> words = new ArrayList<>();
    private final List<BaseGroup> titles = new ArrayList<>();
    private final List<BaseGroup> journals = new ArrayList<>();
    private final List<BaseElement> initials = new ArrayList<>();
    private final List<BaseGroup> initialGroups = new ArrayList<>();
    private final List<BaseElement> secondNames = new ArrayList<>();
    private final List<AuthorElement> authors = new ArrayList<>();
    private final List<AuthorGroup> authorGroups = new ArrayList<>();
    private final List<BaseElement> years = new ArrayList<>();
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

    private final List<BibElement> articles = new ArrayList<>();
    private final List<BibElement> books = new ArrayList<>();

    private final String dash = "[-–]";

    public RegexCollection() {
        words.add(new BaseElement("\\b[A-ZА-Яa-zа-я]+\\b"));

        words.forEach(word -> {
            titles.add(new BaseGroup("(" + word + "\\s*)*" + word, word));
            titles.add(new BaseGroup("(" + word + "\\s*(" + dash + "\\s*)?)*" + word, word));

            journals.add(new BaseGroup("(" + word + "\\.?\\s*)*" + word + "\\.?", word));
        });

        initials.add(new BaseElement("\\b[A-ZА-Я]\\b"));

        initials.forEach(initial -> {
            initialGroups.add(new BaseGroup("(" + initial + "\\.\\s*)?" + initial + "\\.", initial));
            initialGroups.add(new BaseGroup("(" + initial + "\\s*)?" + initial, initial));
        });

        secondNames.add(new BaseElement("\\b[A-ZА-Я][a-zа-я]+\\b"));

        initialGroups.forEach(initialGroup -> secondNames.forEach(secondName -> {
            authors.add(new AuthorElement(initialGroup + "\\s*" + secondName, initialGroup, secondName));
            authors.add(new AuthorElement(secondName + "\\s*" + initialGroup, initialGroup, secondName));
        }));

        authors.forEach(author -> {
            authorGroups.add(new AuthorGroup("(" + author + ",\\s*)*" + author, author));
            authorGroups.add(new AuthorGroup("(" + author + ",\\s*)*" + author + "\\s+and\\s+" + author, author));
            authorGroups.add(new AuthorGroup("(" + author + ",\\s*)*" + author + "\\s+и\\s+" + author, author));
        });

        years.add(new BaseElement("\\b[1-2]\\d{3}\\b"));

        volKeys.add(new BaseElement("\\b[VТ]\\."));

        volValues.add(new BaseElement("\\b\\d+\\b"));

        volKeys.forEach(volKey -> volValues.forEach(volValue -> {
            vols.add(new EntryElement(volKey + "\\s*" + volValue + "\\.?", volKey, volValue));
        }));
        volValues.forEach(volValue -> {
            vols.add(new EntryElement(volValue.toString(), new BaseElement(""), volValue));
        });

        //TODO: numKeys, numValues, nums
        //TODO: otherKeys, otherValues, others

        pageKeys.add(new BaseElement("\\b[PС]\\."));

        pageValues.add(new BaseElement("\\b\\d+" + dash + "\\d+\\b"));

        pageKeys.forEach(pageKey -> pageValues.forEach(pageValue -> {
            pages.add(new EntryElement(pageKey + "\\s*" + pageValue + "\\.?", pageKey, pageValue));
        }));
        pageValues.forEach(pageValue -> {
            pages.add(new EntryElement(pageValue.toString(), new BaseElement(""), pageValue));
        });

        titles.forEach(title -> journals.forEach(journal -> authorGroups.forEach(authorGroup -> years.forEach(year ->
                vols.forEach(vol -> pages.forEach(page -> {
                    final BaseGroup initialGroup = authorGroup.getAuthor().getInitialGroup();
                    final BaseElement secondName = authorGroup.getAuthor().getSecondName();
                    articles.add(new BibElement(
                            secondName + ",\\s*" + initialGroup + "\\s*/\\s*(?<title>" + title +
                                    ")\\s*/\\s*(?<authorGroup>" + authorGroup + ")\\s*//\\s*(?<journal>" + journal +
                                    ")\\s*" + dash + "\\s*(?<year>" + year + ")\\.\\s*" + dash + "\\s*(?<vol>" + vol +
                                    ")\\s*" + dash + "\\s*(?<page>" + page + ")",
                            title,
                            journal,
                            authorGroup,
                            year,
                            vol,
                            null,
                            null,
                            page
                    ));
                    articles.add(new BibElement(
                            "(?<authorGroup>" + authorGroup + ")\\s*(?<year>" + year + ")\\s*(?<title>" + title +
                                    ")\\s+(?<journal>" + journal + ")\\s*(?<vol>" + vol + ")\\s+(?<page>" + page + ")",
                            title,
                            journal,
                            authorGroup,
                            year,
                            vol,
                            null,
                            null,
                            page
                    ));
                }))))));
        journals.forEach(journal -> authorGroups.forEach(authorGroup -> years.forEach(year -> vols.forEach(vol ->
                pages.forEach(page -> {
                    articles.add(new BibElement(
                            "(?<authorGroup>" + authorGroup + ")\\s+(?<year>" + year + ")\\s+(?<journal>" +
                                    journal + ")\\s+(?<vol>" + vol + ")\\s+(?<page>" + page + ")",
                            null,
                            journal,
                            authorGroup,
                            year,
                            vol,
                            null,
                            null,
                            page
                    ));
                })))));
    }

    public List<BaseElement> getWords() {
        return words;
    }

    public List<BaseGroup> getTitles() {
        return titles;
    }

    public List<BaseGroup> getJournals() {
        return journals;
    }

    public List<BaseElement> getInitials() {
        return initials;
    }

    public List<BaseGroup> getInitialGroups() {
        return initialGroups;
    }

    public List<BaseElement> getSecondNames() {
        return secondNames;
    }

    public List<AuthorElement> getAuthors() {
        return authors;
    }

    public List<AuthorGroup> getAuthorGroups() {
        return authorGroups;
    }

    public List<BaseElement> getYears() {
        return years;
    }

    public List<BaseElement> getVolKeys() {
        return volKeys;
    }

    public List<BaseElement> getVolValues() {
        return volValues;
    }

    public List<EntryElement> getVols() {
        return vols;
    }

    public List<BaseElement> getNumKeys() {
        return numKeys;
    }

    public List<BaseElement> getNumValues() {
        return numValues;
    }

    public List<EntryElement> getNums() {
        return nums;
    }

    public List<BaseElement> getOtherKeys() {
        return otherKeys;
    }

    public List<BaseElement> getOtherValues() {
        return otherValues;
    }

    public List<EntryElement> getOthers() {
        return others;
    }

    public List<BaseElement> getPageKeys() {
        return pageKeys;
    }

    public List<BaseElement> getPageValues() {
        return pageValues;
    }

    public List<EntryElement> getPages() {
        return pages;
    }

    public List<BibElement> getArticles() {
        return articles;
    }

    public List<BibElement> getBooks() {
        return books;
    }
}
