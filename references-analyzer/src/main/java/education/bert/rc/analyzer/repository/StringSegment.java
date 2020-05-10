package education.bert.rc.analyzer.repository;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StringSegment that = (StringSegment) o;
        return start == that.start &&
                end == that.end &&
                Objects.equals(text, that.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(text, start, end);
    }
}
