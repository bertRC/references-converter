package education.bert.rc.generator.templates;

import lombok.Data;
import lombok.NonNull;

@Data
public class MyClass {

    @NonNull private final String prefix;
    @NonNull private final String suffix;

    public String concat() {
        return prefix + suffix;
    }
}
