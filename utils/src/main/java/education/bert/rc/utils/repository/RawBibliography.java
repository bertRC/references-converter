package education.bert.rc.utils.repository;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.List;

@RequiredArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
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
        return recognized == null || recognized.getText() == null || recognized.getText().isEmpty();
    }

    public int getCoverage() {
        if (recognized != null) {
            return recognized.getEnd() - recognized.getStart();
        }
        return 0;
    }
}
