package education.bert.rc.analyzer.utils.console;

import education.bert.rc.analyzer.repository.RawBibliography;
import education.bert.rc.analyzer.repository.SegmentComparator;
import education.bert.rc.analyzer.repository.StringSegment;

import java.util.ArrayList;
import java.util.List;

public class BibColors {
    public static void printlnColorize(RawBibliography bibliography) {
        System.out.println(colorize(bibliography));
    }

    public static String colorize(RawBibliography bibliography) {
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

            if (prefix != null) {
                segments.add(new StringSegment(prefix, ConsoleColors.RED));
            }
            if (suffix != null) {
                segments.add(new StringSegment(suffix, ConsoleColors.RED));
            }
            if (title != null) {
                segments.add(new StringSegment(title, ConsoleColors.CYAN_BRIGHT));
            }
            if (journal != null) {
                segments.add(new StringSegment(journal, ConsoleColors.CYAN_BOLD));
            }
            if (authorGroup != null) {
                segments.add(new StringSegment(authorGroup, ConsoleColors.YELLOW));
            }
            if (year != null) {
                segments.add(new StringSegment(year, ConsoleColors.PURPLE));
            }
            if (vol != null) {
                segments.add(new StringSegment(vol, ConsoleColors.GREEN));
            }
            if (num != null) {
                segments.add(new StringSegment(num, ConsoleColors.GREEN_BOLD));
            }
            if (other != null) {
                segments.add(new StringSegment(other, ConsoleColors.GREEN_UNDERLINED));
            }
            if (page != null) {
                segments.add(new StringSegment(page, ConsoleColors.BLUE));
            }

            segments.sort(new SegmentComparator());
            StringBuilder stringBuilder = new StringBuilder(bibliography.getText());
            segments.forEach(segment -> colorizeSegment(stringBuilder, segment));
            return stringBuilder.toString();
        }
        return ConsoleColors.colorize(bibliography.getText(), ConsoleColors.RED);
    }

    private static void colorizeSegment(StringBuilder stringBuilder, StringSegment coloredSegment) {
        stringBuilder.insert(coloredSegment.getEnd(), ConsoleColors.RESET);
        stringBuilder.insert(coloredSegment.getStart(), coloredSegment.toString());
    }
}
