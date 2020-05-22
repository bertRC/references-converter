package education.bert.rc.generator.templates;

import education.bert.rc.utils.repository.Bibliography;
import lombok.Data;
import lombok.NonNull;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class BibTexTemplate implements Template {

    @NonNull private final String name;

    public String generate(@NonNull Bibliography bibliography) {
        return "Еще не реализовано";
    }

    @Override
    public List<String> generate(@NonNull List<Bibliography> bibliographies) {
        return bibliographies.parallelStream().map(this::generate).collect(Collectors.toList());
    }
}
