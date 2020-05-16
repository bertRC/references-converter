package education.bert.rc.analyzer.regexelements;

import lombok.Getter;

@Getter
public class AuthorElement extends BaseElement {

    private final BaseGroup initialGroup;
    private final BaseElement secondName;

    public AuthorElement(String regex, BaseGroup initialGroup, BaseElement secondName) {
        super(regex);
        this.initialGroup = initialGroup;
        this.secondName = secondName;
    }
}
