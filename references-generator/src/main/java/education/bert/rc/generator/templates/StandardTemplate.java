package education.bert.rc.generator.templates;

import education.bert.rc.utils.repository.Bibliography;
import lombok.Data;
import lombok.NonNull;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class StandardTemplate implements Template {

    @NonNull private final String name;
    @NonNull private final BaseTemplate articleTemplate;
    @NonNull private final BaseTemplate bookTemplate;

    @Override
    public String generate(@NonNull Bibliography bibliography) {
        return bibliography.isBook() ? bookTemplate.generate(bibliography) : articleTemplate.generate(bibliography);
    }

    @Override
    public List<String> generate(@NonNull List<Bibliography> bibliographies) {
        return bibliographies.parallelStream().map(this::generate).collect(Collectors.toList());
    }
}
