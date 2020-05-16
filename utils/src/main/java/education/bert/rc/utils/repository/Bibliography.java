package education.bert.rc.utils.repository;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@EqualsAndHashCode
@ToString
public class Bibliography extends RawBibliography {

    private final boolean isBook;
    private final String language;

    public Bibliography(String text, StringSegment recognized, StringSegment prefix, StringSegment suffix,
                        StringSegment title, StringSegment journal, StringSegment authorGroup, StringSegment year,
                        StringSegment vol, StringSegment num, StringSegment other, StringSegment page,
                        List<Author> authors, Entry volEntry, Entry numEntry, Entry otherEntry, Entry pageEntry,
                        boolean isBook, String language) {
        super(text, recognized, prefix, suffix, title, journal, authorGroup, year, vol, num, other, page, authors, volEntry,
                numEntry, otherEntry, pageEntry);
        this.isBook = isBook;
        this.language = language;
    }

    public Bibliography(RawBibliography rawBibliography, boolean isBook, String language) {
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

    public Bibliography(String text) {
        super(text);
        isBook = false;
        language = null;
    }
}
