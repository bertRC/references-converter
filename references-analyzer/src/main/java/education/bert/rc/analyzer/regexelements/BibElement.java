package education.bert.rc.analyzer.regexelements;

import education.bert.rc.utils.repository.Author;
import education.bert.rc.utils.repository.Entry;
import education.bert.rc.utils.repository.RawBibliography;
import education.bert.rc.utils.repository.StringSegment;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

public class BibElement extends BaseElement {

    private final AuthorGroup authorGroup;
    private final EntryElement vol;
    private final EntryElement num;
    private final EntryElement other;
    private final EntryElement page;

    public BibElement(@NonNull String regex, @NonNull AuthorGroup authorGroup, @NonNull EntryElement vol,
                      @NonNull EntryElement num, @NonNull EntryElement other, @NonNull EntryElement page) {
        super(regex);
        this.authorGroup = authorGroup;
        this.vol = vol;
        this.num = num;
        this.other = other;
        this.page = page;
    }

    public RawBibliography parse(@NonNull String text) {
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
            final List<String> authorStrings = this.authorGroup.getAuthor().find(authorGroup.getText());
            authorStrings.forEach(s -> {
                final String secondName = this.authorGroup.getAuthor().getSecondName().find(s).get(0);
                final String initialGroup = this.authorGroup.getAuthor().getInitialGroup().find(s).get(0);
                final List<String> initials = this.authorGroup.getAuthor().getInitialGroup().getBaseElement()
                        .find(initialGroup);
                authors.add(new Author(secondName, initials));
            });

            final String volKey = this.vol.getKey().find(vol.getText()).get(0);
            final String volValue = this.vol.getValue().find(vol.getText()).get(0);
            final Entry volEntry = new Entry(volKey, volValue);

            final String numKey = this.num.getKey().find(num.getText()).get(0);
            final String numValue = this.num.getValue().find(num.getText()).get(0);
            final Entry numEntry = new Entry(numKey, numValue);

            final String otherKey = this.other.getKey().find(other.getText()).get(0);
            final String otherValue = this.other.getValue().find(other.getText()).get(0);
            final Entry otherEntry = new Entry(otherKey, otherValue);

            final String pageKey = this.page.getKey().find(page.getText()).get(0);
            final String pageValue = this.page.getValue().find(page.getText()).get(0);
            final Entry pageEntry = new Entry(pageKey, pageValue);

            return new RawBibliography(
                    text,
                    recognized,
                    prefix,
                    suffix,
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

    private StringSegment extractGroup(@NonNull Matcher matcher, @NonNull String groupName) {
        try {
            if (matcher.group(groupName) != null) {
                return new StringSegment(matcher.group(groupName), matcher.start(groupName), matcher.end(groupName));
            }
        } catch (IllegalArgumentException e) {
            //TODO: print error message
//            System.out.println("[Warning]: " + e.getMessage());
//            e.printStackTrace();
        }
        return StringSegment.emptySegment();
    }

    private StringSegment extractGroup(@NonNull Matcher matcher) {
        return new StringSegment(matcher.group(), matcher.start(), matcher.end());
    }
}
