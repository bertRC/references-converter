package education.bert.rc.utils.repository;

import lombok.*;

import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
public class RawBibliography {

    @NonNull private final String text;

    @NonNull private final StringSegment recognized;
    @NonNull private final StringSegment prefix;
    @NonNull private final StringSegment suffix;

    @NonNull private final StringSegment title;
    @NonNull private final StringSegment journal;
    @NonNull private final StringSegment authorGroup;
    @NonNull private final StringSegment year;
    @NonNull private final StringSegment vol;
    @NonNull private final StringSegment num;
    @NonNull private final StringSegment other;
    @NonNull private final StringSegment page;

    @NonNull private final List<Author> authors;
    @NonNull private final Entry volEntry;
    @NonNull private final Entry numEntry;
    @NonNull private final Entry otherEntry;
    @NonNull private final Entry pageEntry;

    public RawBibliography(@NonNull String text) {
        this.text = text;
        recognized = StringSegment.emptySegment();
        prefix = StringSegment.emptySegment();
        suffix = StringSegment.emptySegment();
        title = StringSegment.emptySegment();
        journal = StringSegment.emptySegment();
        authorGroup = StringSegment.emptySegment();
        year = StringSegment.emptySegment();
        vol = StringSegment.emptySegment();
        num = StringSegment.emptySegment();
        other = StringSegment.emptySegment();
        page = StringSegment.emptySegment();
        authors = Collections.emptyList();
        volEntry = Entry.emptyEntry();
        numEntry = Entry.emptyEntry();
        otherEntry = Entry.emptyEntry();
        pageEntry = Entry.emptyEntry();
    }

    public boolean isEmpty() {
        return recognized.getText().isEmpty();
    }

    public int getCoverage() {
        return recognized.getEnd() - recognized.getStart();
    }
}
