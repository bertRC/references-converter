package education.bert.rc.analyzer.regexelements;

import lombok.Getter;

public class AuthorGroup extends BaseElement {

    @Getter
    private final AuthorElement author;

    public AuthorGroup(String regex, AuthorElement author) {
        super(regex);
        this.author = author;
    }
}
