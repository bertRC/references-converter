package education.bert.rc.analyzer.regexelements;

public class BaseGroup extends BaseElement {

    private final BaseElement baseElement;

    public BaseGroup(String regex, BaseElement baseElement) {
        super(regex);
        this.baseElement = baseElement;
    }

    public BaseElement getBaseElement() {
        return baseElement;
    }
}
