package education.bert.rc.generator.templates;

import education.bert.rc.utils.repository.Bibliography;
import lombok.NonNull;

import java.util.List;

public interface Template {

    String getName();

    List<String> generate(@NonNull List<Bibliography> bibliographies);
}
