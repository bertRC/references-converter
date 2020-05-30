package education.bert.rc.utils.colors.css;

import education.bert.rc.utils.repository.RawBibliography;
import education.bert.rc.utils.repository.SegmentComparator;
import education.bert.rc.utils.repository.StringSegment;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;

public class HtmlColors {

    //<span style="background: linear-gradient(transparent, red, transparent)">
    public static final String ERROR_COLOR = "rgba(255,64,64,0.4)";
    public static final String TITLE_COLOR = "rgba(23,162,184,0.2)";
    public static final String JOURNAL_COLOR = "rgba(0,32,164,0.2)";
    public static final String AUTHOR_COLOR = "rgba(230,192,0,0.2)";
    public static final String YEAR_COLOR = "rgba(210,0,192,0.2)";
    public static final String VOLUME_COLOR = "rgba(40,167,69,0.2)";
    public static final String NUMBER_COLOR = "rgba(110,170,40,0.2)";
    public static final String OTHER_COLOR = "rgba(0,150,0,0.2)";
    public static final String PAGE_COLOR = "rgba(0,123,255,0.2)";

    public static String colorize(@NonNull String text, @NonNull String color) {
        return "<span style=\"background-color: " + color + "\">" + text + "</span>";
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
                segments.add(new StringSegment(prefix, ERROR_COLOR));
            }
            if (!suffix.getText().isEmpty()) {
                segments.add(new StringSegment(suffix, ERROR_COLOR));
            }
            if (!title.getText().isEmpty()) {
                segments.add(new StringSegment(title, TITLE_COLOR));
            }
            if (!journal.getText().isEmpty()) {
                segments.add(new StringSegment(journal, JOURNAL_COLOR));
            }
            if (!authorGroup.getText().isEmpty()) {
                segments.add(new StringSegment(authorGroup, AUTHOR_COLOR));
            }
            if (!year.getText().isEmpty()) {
                segments.add(new StringSegment(year, YEAR_COLOR));
            }
            if (!vol.getText().isEmpty()) {
                segments.add(new StringSegment(vol, VOLUME_COLOR));
            }
            if (!num.getText().isEmpty()) {
                segments.add(new StringSegment(num, NUMBER_COLOR));
            }
            if (!other.getText().isEmpty()) {
                segments.add(new StringSegment(other, OTHER_COLOR));
            }
            if (!page.getText().isEmpty()) {
                segments.add(new StringSegment(page, PAGE_COLOR));
            }

            segments.sort(new SegmentComparator());
            StringBuilder stringBuilder = new StringBuilder(bibliography.getText());
            segments.forEach(segment -> colorizeSegment(stringBuilder, segment));
            return stringBuilder.toString();
        }
        return colorize(bibliography.getText(), ERROR_COLOR);
    }

    private static void colorizeSegment(@NonNull StringBuilder stringBuilder, @NonNull StringSegment coloredSegment) {
        stringBuilder.insert(coloredSegment.getEnd(), "</span>");
        stringBuilder.insert(coloredSegment.getStart(), "<span style=\"background-color: " + coloredSegment.getText() + "\">");
    }
}
