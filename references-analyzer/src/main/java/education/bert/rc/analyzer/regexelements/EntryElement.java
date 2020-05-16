package education.bert.rc.analyzer.regexelements;

import lombok.Getter;

@Getter
public class EntryElement extends BaseElement {

    private final BaseElement key;
    private final BaseElement value;

    public EntryElement(String regex, BaseElement key, BaseElement value) {
        super(regex);
        this.key = key;
        this.value = value;
    }
}
