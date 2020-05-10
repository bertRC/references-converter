package education.bert.rc.analyzer.service;

import education.bert.rc.analyzer.file.service.FileService;
import education.bert.rc.analyzer.repository.Author;
import education.bert.rc.analyzer.repository.Bibliography;
import education.bert.rc.analyzer.repository.Entry;
import education.bert.rc.analyzer.repository.StringSegment;
import education.bert.rc.analyzer.utils.console.BibColors;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestAnalyzer {

    private final Analyzer analyzer = new Analyzer();
    private final FileService fileService = new FileService("testUpload");

    @Disabled
    @Test
    public void testWithoutAssertions() {
        final long startTime = System.currentTimeMillis();
        final List<Bibliography> bibliographies = analyzer.analyze(fileService.readFile("pool.txt"));
        final long duration = System.currentTimeMillis() - startTime;

        System.out.println("[INFO] Analysis took " + duration + " milliseconds");
        bibliographies.forEach(BibColors::printlnColorize);
    }

    @Test
    public void testAnalyze() {
        List<Bibliography> bibExpected = new ArrayList<>();
        bibExpected.add(new Bibliography(
                "Никто: абсолютно никто",
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                false,
                null
        ));
        bibExpected.add(new Bibliography(
                "Liebman S A, Foltz C R, Reuwer J F and Obremski R J 1971 Macromolecules 4 134-8",
                new StringSegment("Liebman S A, Foltz C R, Reuwer J F and Obremski R J 1971 Macromolecules 4 134-8", 0, 79),
                null,
                null,
                null,
                new StringSegment("Macromolecules", 57, 71),
                new StringSegment("Liebman S A, Foltz C R, Reuwer J F and Obremski R J", 0, 51),
                new StringSegment("1971", 52, 56),
                new StringSegment("4", 72, 73),
                null,
                null,
                new StringSegment("134-8", 74, 79),
                Arrays.asList(
                        new Author("Liebman", Arrays.asList("S", "A")),
                        new Author("Foltz", Arrays.asList("C", "R")),
                        new Author("Reuwer", Arrays.asList("J", "F")),
                        new Author("Obremski", Arrays.asList("R", "J"))
                ),
                new Entry("", "4"),
                null,
                null,
                new Entry("", "134-8"),
                false,
                "latin"
        ));
        bibExpected.add(new Bibliography(
                "1. Велев, О.Д. / Пористый диоксид кремния полученный темплатным методом / О. Д. Велев, И.П. Сидоров, Р.Ф.  Лобо, А.М.Ленхофф // Хим. Мат. - 1998. - Т. 10. - С. 3597-3602. [http...]",
                new StringSegment("Велев, О.Д. / Пористый диоксид кремния полученный темплатным методом / О. Д. Велев, И.П. Сидоров, Р.Ф.  Лобо, А.М.Ленхофф // Хим. Мат. - 1998. - Т. 10. - С. 3597-3602.", 3, 170),
                new StringSegment("1. ", 0, 3),
                new StringSegment(" [http...]", 170, 180),
                new StringSegment("Пористый диоксид кремния полученный темплатным методом", 17, 71),
                new StringSegment("Хим. Мат.", 128, 137),
                new StringSegment("О. Д. Велев, И.П. Сидоров, Р.Ф.  Лобо, А.М.Ленхофф", 74, 124),
                new StringSegment("1998", 140, 144),
                new StringSegment("Т. 10.", 148, 154),
                null,
                null,
                new StringSegment("С. 3597-3602.", 157, 170),
                Arrays.asList(
                        new Author("Велев", Arrays.asList("О", "Д")),
                        new Author("Сидоров", Arrays.asList("И", "П")),
                        new Author("Лобо", Arrays.asList("Р", "Ф")),
                        new Author("Ленхофф", Arrays.asList("А", "М"))
                ),
                new Entry("Т.", "10"),
                null,
                null,
                new Entry("С.", "3597-3602"),
                false,
                "cyrillic"
        ));

        final List<Bibliography> bibActual = analyzer.analyze(fileService.readFile("testPool.txt"));

        assertEquals(bibExpected, bibActual);
    }
}
