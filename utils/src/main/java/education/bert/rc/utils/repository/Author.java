package education.bert.rc.utils.repository;

import lombok.Data;

import java.util.List;

@Data
public class Author {
    private final String secondName;
    private final List<String> initials;
}
