package education.bert.rc.utils.colors.console;

import education.bert.rc.utils.repository.RawBibliography;
import education.bert.rc.utils.repository.SegmentComparator;
import education.bert.rc.utils.repository.StringSegment;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;

public class BibConsoleColors {
    public static void printlnColorize(@NonNull RawBibliography bibliography) {
        System.out.println(colorize(bibliography));
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
                segments.add(new StringSegment(prefix, ConsoleColors.RED));
            }
            if (!suffix.getText().isEmpty()) {
                segments.add(new StringSegment(suffix, ConsoleColors.RED));
            }
            if (!title.getText().isEmpty()) {
                segments.add(new StringSegment(title, ConsoleColors.CYAN_BRIGHT));
            }
            if (!journal.getText().isEmpty()) {
                segments.add(new StringSegment(journal, ConsoleColors.CYAN_BOLD));
            }
            if (!authorGroup.getText().isEmpty()) {
                segments.add(new StringSegment(authorGroup, ConsoleColors.YELLOW));
            }
            if (!year.getText().isEmpty()) {
                segments.add(new StringSegment(year, ConsoleColors.PURPLE));
            }
            if (!vol.getText().isEmpty()) {
                segments.add(new StringSegment(vol, ConsoleColors.GREEN));
            }
            if (!num.getText().isEmpty()) {
                segments.add(new StringSegment(num, ConsoleColors.GREEN_BOLD));
            }
            if (!other.getText().isEmpty()) {
                segments.add(new StringSegment(other, ConsoleColors.GREEN_UNDERLINED));
            }
            if (!page.getText().isEmpty()) {
                segments.add(new StringSegment(page, ConsoleColors.BLUE));
            }

            segments.sort(new SegmentComparator());
            StringBuilder stringBuilder = new StringBuilder(bibliography.getText());
            segments.forEach(segment -> colorizeSegment(stringBuilder, segment));
            return stringBuilder.toString();
        }
        return ConsoleColors.colorize(bibliography.getText(), ConsoleColors.RED);
    }

    private static void colorizeSegment(@NonNull StringBuilder stringBuilder, @NonNull StringSegment coloredSegment) {
        stringBuilder.insert(coloredSegment.getEnd(), ConsoleColors.RESET);
        stringBuilder.insert(coloredSegment.getStart(), coloredSegment.getText());
    }
}
