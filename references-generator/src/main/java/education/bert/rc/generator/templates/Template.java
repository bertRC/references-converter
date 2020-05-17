package education.bert.rc.generator.templates;

import education.bert.rc.utils.repository.Bibliography;
import lombok.Data;

import java.util.List;

@Data
public abstract class Template {

    private String name;

    public String generate(Bibliography bibliography) {
        return null;
    }

    public List<String> generate(List<Bibliography> bibliographies) {
        return null;
    }
}
