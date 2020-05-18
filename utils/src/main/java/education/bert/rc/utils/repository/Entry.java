package education.bert.rc.utils.repository;

import lombok.Data;
import lombok.NonNull;

@Data
public class Entry {
    @NonNull private final String key;
    @NonNull private final String value;

    public static Entry emptyEntry() {
        return new Entry("", "");
    }
}
