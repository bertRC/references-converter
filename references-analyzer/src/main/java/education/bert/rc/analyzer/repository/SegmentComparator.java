package education.bert.rc.analyzer.repository;

import java.util.Comparator;

public class SegmentComparator implements Comparator<StringSegment> {
    @Override
    public int compare(StringSegment o1, StringSegment o2) {
        return o2.getStart() - o1.getStart();
    }
}
