package education.bert.rc.analyzer.regexelements;

public class AuthorGroup extends BaseElement {

    private final AuthorElement author;

    public AuthorGroup(String regex, AuthorElement author) {
        super(regex);
        this.author = author;
    }

    public AuthorElement getAuthor() {
        return author;
    }
}
