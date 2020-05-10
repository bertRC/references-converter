package education.bert.rc.analyzer.repository;

import java.util.List;

public class RawBibliography {

    private final String text;

    private final StringSegment recognized;
    private final StringSegment prefix;
    private final StringSegment suffix;

    private final StringSegment title;
    private final StringSegment journal;
    private final StringSegment authorGroup;
    private final StringSegment year;
    private final StringSegment vol;
    private final StringSegment num;
    private final StringSegment other;
    private final StringSegment page;

    private final List<Author> authors;
    private final Entry volEntry;
    private final Entry numEntry;
    private final Entry otherEntry;
    private final Entry pageEntry;

    public RawBibliography(String text, StringSegment recognized, StringSegment prefix, StringSegment suffix,
                           StringSegment title, StringSegment journal, StringSegment authorGroup, StringSegment year,
                           StringSegment vol, StringSegment num, StringSegment other, StringSegment page,
                           List<Author> authors, Entry volEntry, Entry numEntry, Entry otherEntry, Entry pageEntry) {
        this.text = text;
        this.recognized = recognized;
        this.prefix = prefix;
        this.suffix = suffix;
        this.title = title;
        this.journal = journal;
        this.authorGroup = authorGroup;
        this.year = year;
        this.vol = vol;
        this.num = num;
        this.other = other;
        this.page = page;
        this.authors = authors;
        this.volEntry = volEntry;
        this.numEntry = numEntry;
        this.otherEntry = otherEntry;
        this.pageEntry = pageEntry;
    }

    public RawBibliography(String text) {
        this.text = text;
        recognized = null;
        prefix = null;
        suffix = null;
        title = null;
        journal = null;
        authorGroup = null;
        year = null;
        vol = null;
        num = null;
        other = null;
        page = null;
        authors = null;
        volEntry = null;
        numEntry = null;
        otherEntry = null;
        pageEntry = null;
    }

    public boolean isEmpty() {
        return recognized == null || recognized.toString() == null || recognized.toString().isEmpty();
    }

    public int getCoverage() {
        if (recognized != null) {
            return recognized.getEnd() - recognized.getStart();
        }
        return 0;
    }

    public String getText() {
        return text;
    }

    public StringSegment getRecognized() {
        return recognized;
    }

    public StringSegment getPrefix() {
        return prefix;
    }

    public StringSegment getSuffix() {
        return suffix;
    }

    public StringSegment getTitle() {
        return title;
    }

    public StringSegment getJournal() {
        return journal;
    }

    public StringSegment getAuthorGroup() {
        return authorGroup;
    }

    public StringSegment getYear() {
        return year;
    }

    public StringSegment getVol() {
        return vol;
    }

    public StringSegment getNum() {
        return num;
    }

    public StringSegment getOther() {
        return other;
    }

    public StringSegment getPage() {
        return page;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public Entry getVolEntry() {
        return volEntry;
    }

    public Entry getNumEntry() {
        return numEntry;
    }

    public Entry getOtherEntry() {
        return otherEntry;
    }

    public Entry getPageEntry() {
        return pageEntry;
    }

    @Override
    public String toString() {
        return "RawBibliography{" +
                "text='" + text +
                "', recognized='" + recognized +
                "', prefix='" + prefix +
                "', suffix='" + suffix +
                "', title='" + title +
                "', journal='" + journal +
                "', authorGroup='" + authorGroup +
                "', year='" + year +
                "', vol='" + vol +
                "', num='" + num +
                "', other='" + other +
                "', page='" + page +
                "', authors=" + authors +
                ", volEntry=" + volEntry +
                ", numEntry=" + numEntry +
                ", otherEntry=" + otherEntry +
                ", pageEntry=" + pageEntry +
                '}';
    }
}
