package education.bert.rc.analyzer.regexelements;

import lombok.Getter;
import lombok.NonNull;

@Getter
public class EntryElement extends BaseElement {

    private final BaseElement key;
    private final BaseElement value;

    public EntryElement(@NonNull String regex, @NonNull BaseElement key, @NonNull BaseElement value) {
        super(regex);
        this.key = key;
        this.value = value;
    }

    public static EntryElement emptyElement() {
        return new EntryElement("", new BaseElement(""), new BaseElement(""));
    }
}
