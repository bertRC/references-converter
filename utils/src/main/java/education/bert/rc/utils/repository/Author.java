package education.bert.rc.utils.repository;

import lombok.Data;
import lombok.NonNull;

import java.util.List;

@Data
public class Author {
    @NonNull private final String secondName;
    @NonNull private final List<String> initials;
}
