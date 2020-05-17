package education.bert.rc.generator.templates;

import lombok.Data;

@Data
public class MyClass {

    private final String prefix;
    private final String suffix;

    public String concat() {
        return prefix + suffix;
    }
}
