package education.bert.rc.generator.collection;

import education.bert.rc.utils.repository.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestTemplateCollection {

    @Test
    public void testGost() {
        final TemplateCollection collection = new TemplateCollection();
        final String expected = "Белев, Д.О. / Некоторые анализы средневековой системы дальше не стал / Д.О. Белев, И.П. Сидоров, Ф.Р. Мобо, М.А. Венхофф // Био. Мат.. – 1998. – Т. 14. – С. 3397-3503.";
        List<Bibliography> bibliographies = new ArrayList<>();
        bibliographies.add(new Bibliography(
                "1. Белев, Д.О. / Некоторые анализы средневековой системы дальше не стал / Д. О. Белев, И.П. Сидоров, Ф.Р.  Мобо, М.А.Венхофф // Био. Мат. - 1998. - Т. 14. - С. 3397-3503. [http...]",
                new StringSegment("Белев, Д.О. / Некоторые анализы средневековой системы дальше не стал / Д. О. Белев, И.П. Сидоров, Ф.Р.  Мобо, М.А.Венхофф // Био. Мат. - 1998. - Т. 14. - С. 3397-3503.", 3, 170),
                new StringSegment("1. ", 0, 3),
                new StringSegment(" [http...]", 170, 180),
                new StringSegment("Некоторые анализы средневековой системы дальше не стал", 17, 71),
                new StringSegment("Био. Мат.", 128, 137),
                new StringSegment("Д. О. Белев, И.П. Сидоров, Ф.Р.  Мобо, М.А.Венхофф", 74, 124),
                new StringSegment("1998", 140, 144),
                new StringSegment("Т. 14.", 148, 154),
                StringSegment.emptySegment(),
                StringSegment.emptySegment(),
                new StringSegment("С. 3397-3503.", 157, 170),
                Arrays.asList(
                        new Author("Белев", Arrays.asList("Д", "О")),
                        new Author("Сидоров", Arrays.asList("И", "П")),
                        new Author("Мобо", Arrays.asList("Ф", "Р")),
                        new Author("Венхофф", Arrays.asList("М", "А"))
                ),
                new Entry("Т", "14"),
                Entry.emptyEntry(),
                Entry.emptyEntry(),
                new Entry("С", "3397-3503"),
                false,
                Language.CYRILLIC
        ));
        final String result = collection.getAll().get(1).generate(bibliographies).get(0);
        assertEquals(expected, result);
    }
}
