package education.bert.rc.analyzer.regexelements;

import lombok.Getter;

public class BaseGroup extends BaseElement {

    @Getter
    private final BaseElement baseElement;

    public BaseGroup(String regex, BaseElement baseElement) {
        super(regex);
        this.baseElement = baseElement;
    }
}
