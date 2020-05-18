package education.bert.rc.analyzer.regexelements;

import lombok.Getter;
import lombok.NonNull;

public class BaseGroup extends BaseElement {

    @Getter
    private final BaseElement baseElement;

    public BaseGroup(@NonNull String regex, @NonNull BaseElement baseElement) {
        super(regex);
        this.baseElement = baseElement;
    }
}
