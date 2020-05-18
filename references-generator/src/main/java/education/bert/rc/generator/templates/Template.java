package education.bert.rc.generator.templates;

import education.bert.rc.utils.repository.Bibliography;
import lombok.Data;
import lombok.NonNull;

import java.util.Collections;
import java.util.List;

@Data
public abstract class Template {

    @NonNull private String name = "";

    public String generate(@NonNull Bibliography bibliography) {
        return "";
    }

    public List<String> generate(@NonNull List<Bibliography> bibliographies) {
        return Collections.emptyList();
    }
}
