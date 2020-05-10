package education.bert.rc.analyzer.repository;

public class StringSegment {

    private final String text;
    private final int start;
    private final int end;

    public StringSegment(String text, int start, int end) {
        this.text = text;
        this.start = start;
        this.end = end;
    }

    public StringSegment(StringSegment oldStringSegment, String newText) {
        text = newText;
        start = oldStringSegment.getStart();
        end = oldStringSegment.getEnd();
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }

    @Override
    public String toString() {
        return text;
    }
}
