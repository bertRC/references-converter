package education.bert.rc.generator.templates;

import education.bert.rc.utils.repository.*;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestTemplates {

    @Test
    public void test() {
        final String template = "<firstAuthor=\"Ivanov, I.I.\"> / <title> / <authors=\"I.I. Ivanov\"> // <journal> - <year>.<vol=\" - Т. ?.\"><num=\" - №. ?.\"><pages=\" - С. ?\">.";
        final String expected = "Белев, Д.О. / Некоторые анализы средневековой системы дальше не стал / Д.О. Белев, И.П. Сидоров, Ф.Р. Мобо, М.А. Венхофф // Био. Мат. - 1998. - Т. 14. - С. 3397-3503.";
        final Bibliography bibliography = new Bibliography(
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
        );
        assertEquals(expected, Templates.generate(bibliography, template));
    }
}
