package education.bert.rc.analyzer.regexelements;

import lombok.Getter;
import lombok.NonNull;

@Getter
public class AuthorElement extends BaseElement {

    private final BaseGroup initialGroup;
    private final BaseElement secondName;

    public AuthorElement(@NonNull String regex, @NonNull BaseGroup initialGroup, @NonNull BaseElement secondName) {
        super(regex);
        this.initialGroup = initialGroup;
        this.secondName = secondName;
    }
}
