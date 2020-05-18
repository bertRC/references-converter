package education.bert.rc.utils.repository;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

import java.util.List;

@Getter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Bibliography extends RawBibliography {

    private final boolean isBook;
    private final Language language;

    public Bibliography(@NonNull String text, @NonNull StringSegment recognized, @NonNull StringSegment prefix,
                        @NonNull StringSegment suffix, @NonNull StringSegment title, @NonNull StringSegment journal,
                        @NonNull StringSegment authorGroup, @NonNull StringSegment year, @NonNull StringSegment vol,
                        @NonNull StringSegment num, @NonNull StringSegment other, @NonNull StringSegment page,
                        @NonNull List<Author> authors, @NonNull Entry volEntry, @NonNull Entry numEntry,
                        @NonNull Entry otherEntry, @NonNull Entry pageEntry, boolean isBook, @NonNull Language language) {
        super(text, recognized, prefix, suffix, title, journal, authorGroup, year, vol, num, other, page, authors, volEntry,
                numEntry, otherEntry, pageEntry);
        this.isBook = isBook;
        this.language = language;
    }

    public Bibliography(@NonNull RawBibliography rawBibliography, boolean isBook, @NonNull Language language) {
        super(
                rawBibliography.getText(),
                rawBibliography.getRecognized(),
                rawBibliography.getPrefix(),
                rawBibliography.getSuffix(),
                rawBibliography.getTitle(),
                rawBibliography.getJournal(),
                rawBibliography.getAuthorGroup(),
                rawBibliography.getYear(),
                rawBibliography.getVol(),
                rawBibliography.getNum(),
                rawBibliography.getOther(),
                rawBibliography.getPage(),
                rawBibliography.getAuthors(),
                rawBibliography.getVolEntry(),
                rawBibliography.getNumEntry(),
                rawBibliography.getOtherEntry(),
                rawBibliography.getPageEntry()
        );
        this.isBook = isBook;
        this.language = language;
    }

    public Bibliography(@NonNull String text) {
        super(text);
        isBook = false;
        language = Language.LATIN;
    }
}
