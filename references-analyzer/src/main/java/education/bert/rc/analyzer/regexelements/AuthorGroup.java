package education.bert.rc.analyzer.regexelements;

import lombok.Getter;
import lombok.NonNull;

public class AuthorGroup extends BaseElement {

    @Getter
    private final AuthorElement author;

    public AuthorGroup(@NonNull String regex, @NonNull AuthorElement author) {
        super(regex);
        this.author = author;
    }
}
