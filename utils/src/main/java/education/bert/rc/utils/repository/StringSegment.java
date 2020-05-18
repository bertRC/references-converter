package education.bert.rc.utils.repository;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

@Getter
@EqualsAndHashCode
@ToString
public class StringSegment {

    private final String text;
    private final int start;
    private final int end;

    public StringSegment(@NonNull String text, int start, int end) {
        this.text = text;
        this.start = text.isEmpty() ? 0 : start;
        this.end = text.isEmpty() ? 0 : end;
    }

    public StringSegment(@NonNull StringSegment oldStringSegment, @NonNull String newText) {
        text = newText;
        start = oldStringSegment.getStart();
        end = oldStringSegment.getEnd();
    }

    public static StringSegment emptySegment() {
        return new StringSegment("", 0, 0);
    }
}
