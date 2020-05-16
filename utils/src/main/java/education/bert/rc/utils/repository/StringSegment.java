package education.bert.rc.utils.repository;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
public class StringSegment {

    private final String text;
    private final int start;
    private final int end;

    public StringSegment(StringSegment oldStringSegment, String newText) {
        text = newText;
        start = oldStringSegment.getStart();
        end = oldStringSegment.getEnd();
    }
}
