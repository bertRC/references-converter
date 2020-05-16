package education.bert.rc.analyzer.regexelements;

import education.bert.rc.utils.list.ListUtil;
import education.bert.rc.utils.repository.Author;
import education.bert.rc.utils.repository.Entry;
import education.bert.rc.utils.repository.RawBibliography;
import education.bert.rc.utils.repository.StringSegment;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

public class BibElement extends BaseElement {

    private final AuthorGroup authorGroup;
    private final EntryElement vol;
    private final EntryElement num;
    private final EntryElement other;
    private final EntryElement page;

    public BibElement(String regex, AuthorGroup authorGroup, EntryElement vol, EntryElement num, EntryElement other,
                      EntryElement page) {
        super(regex);
        this.authorGroup = authorGroup;
        this.vol = vol;
        this.num = num;
        this.other = other;
        this.page = page;
    }

    public RawBibliography parse(String text) {
        final Matcher matcher = getPattern().matcher(text);
        if (matcher.find()) {
            final StringSegment recognized = extractGroup(matcher);
            final StringSegment prefix = new StringSegment(text.substring(0, matcher.start()), 0, matcher.start());
            final StringSegment suffix = new StringSegment(text.substring(matcher.end()), matcher.end(), text.length());

            final StringSegment title = extractGroup(matcher, "title");
            final StringSegment journal = extractGroup(matcher, "journal");
            final StringSegment authorGroup = extractGroup(matcher, "authorGroup");
            final StringSegment year = extractGroup(matcher, "year");
            final StringSegment vol = extractGroup(matcher, "vol");
            final StringSegment num = extractGroup(matcher, "num");
            final StringSegment other = extractGroup(matcher, "other");
            final StringSegment page = extractGroup(matcher, "page");

            List<Author> authors = new ArrayList<>();
            if (authorGroup != null) {
                final List<String> authorStrings = this.authorGroup.getAuthor().find(authorGroup.toString());
                authorStrings.forEach(s -> {
                    final String secondName =
                            ListUtil.getFirst(this.authorGroup.getAuthor().getSecondName().find(s), "");
                    final String initialGroup =
                            ListUtil.getFirst(this.authorGroup.getAuthor().getInitialGroup().find(s), "");
                    final List<String> initials = this.authorGroup.getAuthor().getInitialGroup().getBaseElement()
                            .find(initialGroup);
                    authors.add(new Author(secondName, initials));
                });
            }

            Entry volEntry = null;
            if (vol != null) {
                final String volKey = ListUtil.getFirst(this.vol.getKey().find(vol.toString()), "");
                final String volValue = ListUtil.getFirst(this.vol.getValue().find(vol.toString()), "");
                volEntry = new Entry(volKey, volValue);
            }

            Entry numEntry = null;
            if (num != null) {
                final String numKey = ListUtil.getFirst(this.num.getKey().find(num.toString()), "");
                final String numValue = ListUtil.getFirst(this.num.getValue().find(num.toString()), "");
                numEntry = new Entry(numKey, numValue);
            }

            Entry otherEntry = null;
            if (other != null) {
                final String otherKey = ListUtil.getFirst(this.other.getKey().find(other.toString()), "");
                final String otherValue = ListUtil.getFirst(this.other.getValue().find(other.toString()), "");
                otherEntry = new Entry(otherKey, otherValue);
            }

            Entry pageEntry = null;
            if (page != null) {
                final String pageKey = ListUtil.getFirst(this.page.getKey().find(page.toString()), "");
                final String pageValue = ListUtil.getFirst(this.page.getValue().find(page.toString()), "");
                pageEntry = new Entry(pageKey, pageValue);
            }

            return new RawBibliography(
                    text,
                    recognized,
                    prefix.getText().isEmpty() ? null : prefix,
                    suffix.getText().isEmpty() ? null : suffix,
                    title,
                    journal,
                    authorGroup,
                    year,
                    vol,
                    num,
                    other,
                    page,
                    authors,
                    volEntry,
                    numEntry,
                    otherEntry,
                    pageEntry
            );
        }
        return new RawBibliography(text);
    }

    private StringSegment extractGroup(Matcher matcher, String groupName) {
        try {
            if (matcher.group(groupName) != null) {
                return new StringSegment(matcher.group(groupName), matcher.start(groupName), matcher.end(groupName));
            }
        } catch (IllegalArgumentException e) {
            //TODO: print error message
//            System.out.println("[Warning]: " + e.getMessage());
//            e.printStackTrace();
        }
        return null;
    }

    private StringSegment extractGroup(Matcher matcher) {
        return new StringSegment(matcher.group(), matcher.start(), matcher.end());
    }
}
