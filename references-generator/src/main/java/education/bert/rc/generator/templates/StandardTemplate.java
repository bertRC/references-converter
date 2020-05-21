package education.bert.rc.generator.templates;

import education.bert.rc.utils.repository.Bibliography;
import education.bert.rc.utils.repository.Language;
import lombok.Data;
import lombok.NonNull;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class StandardTemplate implements Template {

    @NonNull private final String name;
    @NonNull private String articleLatinTemplate;
    @NonNull private String articleCyrillicTemplate;
    @NonNull private String bookLatinTemplate;
    @NonNull private String bookCyrillicTemplate;

    @Override
    public String generate(@NonNull Bibliography bibliography) {
        if (!bibliography.isEmpty()) {
            if (bibliography.getLanguage().equals(Language.LATIN)) {
                return bibliography.isBook() ?
                        Templates.generate(bibliography, bookLatinTemplate) :
                        Templates.generate(bibliography, articleLatinTemplate);
            }
            return bibliography.isBook() ?
                    Templates.generate(bibliography, bookCyrillicTemplate) :
                    Templates.generate(bibliography, articleCyrillicTemplate);
        }
        return "";
    }

    @Override
    public List<String> generate(@NonNull List<Bibliography> bibliographies) {
        return bibliographies.parallelStream().map(this::generate).collect(Collectors.toList());
    }
}
