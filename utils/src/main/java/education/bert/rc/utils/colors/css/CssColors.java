package education.bert.rc.utils.colors.css;

import education.bert.rc.utils.repository.RawBibliography;
import education.bert.rc.utils.repository.SegmentComparator;
import education.bert.rc.utils.repository.StringSegment;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;

public class CssColors {

    public static final String ERROR_STYLE = "bib-error";
    public static final String TITLE_STYLE = "bib-title";
    public static final String JOURNAL_STYLE = "bib-journal";
    public static final String AUTHOR_STYLE = "bib-author";
    public static final String YEAR_STYLE = "bib-year";
    public static final String VOLUME_STYLE = "bib-volume";
    public static final String NUMBER_STYLE = "bib-number";
    public static final String OTHER_STYLE = "bib-other";
    public static final String PAGE_STYLE = "bib-page";

    public static String colorize(@NonNull String text, @NonNull String styleClass) {
        return "<span class=\"" + styleClass + "\">" + text + "</span>";
    }

    public static String colorize(@NonNull RawBibliography bibliography) {
        if (!bibliography.isEmpty()) {
            final List<StringSegment> segments = new ArrayList<>();
            final StringSegment prefix = bibliography.getPrefix();
            final StringSegment suffix = bibliography.getSuffix();
            final StringSegment title = bibliography.getTitle();
            final StringSegment journal = bibliography.getJournal();
            final StringSegment authorGroup = bibliography.getAuthorGroup();
            final StringSegment year = bibliography.getYear();
            final StringSegment vol = bibliography.getVol();
            final StringSegment num = bibliography.getNum();
            final StringSegment other = bibliography.getOther();
            final StringSegment page = bibliography.getPage();

            if (!prefix.getText().isEmpty()) {
                segments.add(new StringSegment(prefix, ERROR_STYLE));
            }
            if (!suffix.getText().isEmpty()) {
                segments.add(new StringSegment(suffix, ERROR_STYLE));
            }
            if (!title.getText().isEmpty()) {
                segments.add(new StringSegment(title, TITLE_STYLE));
            }
            if (!journal.getText().isEmpty()) {
                segments.add(new StringSegment(journal, JOURNAL_STYLE));
            }
            if (!authorGroup.getText().isEmpty()) {
                segments.add(new StringSegment(authorGroup, AUTHOR_STYLE));
            }
            if (!year.getText().isEmpty()) {
                segments.add(new StringSegment(year, YEAR_STYLE));
            }
            if (!vol.getText().isEmpty()) {
                segments.add(new StringSegment(vol, VOLUME_STYLE));
            }
            if (!num.getText().isEmpty()) {
                segments.add(new StringSegment(num, NUMBER_STYLE));
            }
            if (!other.getText().isEmpty()) {
                segments.add(new StringSegment(other, OTHER_STYLE));
            }
            if (!page.getText().isEmpty()) {
                segments.add(new StringSegment(page, PAGE_STYLE));
            }

            segments.sort(new SegmentComparator());
            StringBuilder stringBuilder = new StringBuilder(bibliography.getText());
            segments.forEach(segment -> colorizeSegment(stringBuilder, segment));
            return stringBuilder.toString();
        }
        return colorize(bibliography.getText(), ERROR_STYLE);
    }

    private static void colorizeSegment(@NonNull StringBuilder stringBuilder, @NonNull StringSegment coloredSegment) {
        stringBuilder.insert(coloredSegment.getEnd(), "</span>");
        stringBuilder.insert(coloredSegment.getStart(), "<span class=\"" + coloredSegment.getText() + "\">");
    }
}
