package education.bert.rc.analyzer.regexelements;

public class EntryElement extends BaseElement {

    private final BaseElement key;
    private final BaseElement value;

    public EntryElement(String regex, BaseElement key, BaseElement value) {
        super(regex);
        this.key = key;
        this.value = value;
    }

    public BaseElement getKey() {
        return key;
    }

    public BaseElement getValue() {
        return value;
    }
}
