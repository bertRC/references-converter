package education.bert.rc.analyzer.service;

import education.bert.rc.utils.colors.console.BibConsoleColors;
import education.bert.rc.utils.file.service.FileService;
import education.bert.rc.utils.repository.*;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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

        final long emptyCount = bibliographies.stream().filter(RawBibliography::isEmpty).count();
        final int totalCount = bibliographies.size();
        final long recognition = 100 * (totalCount - emptyCount) / totalCount;
        final Integer symbolCoverage = bibliographies.stream().map(RawBibliography::getCoverage).reduce(0, Integer::sum);
        System.out.println("[INFO] Recognized: " + (totalCount - emptyCount) + "/" + totalCount);
        System.out.println("[INFO] Symbols recognized: " + symbolCoverage);
        System.out.println("[INFO] Recognition: " + recognition + "%");
        System.out.println("[INFO] Analysis took " + duration + " milliseconds");
        bibliographies.forEach(BibConsoleColors::printlnColorize);
    }

    @Test
    public void testAnalyze() {
        List<Bibliography> bibExpected = new ArrayList<>();
        bibExpected.add(new Bibliography(
                "Никто: абсолютно никто",
                StringSegment.emptySegment(),
                StringSegment.emptySegment(),
                StringSegment.emptySegment(),
                StringSegment.emptySegment(),
                StringSegment.emptySegment(),
                StringSegment.emptySegment(),
                StringSegment.emptySegment(),
                StringSegment.emptySegment(),
                StringSegment.emptySegment(),
                StringSegment.emptySegment(),
                StringSegment.emptySegment(),
                Collections.emptyList(),
                Entry.emptyEntry(),
                Entry.emptyEntry(),
                Entry.emptyEntry(),
                Entry.emptyEntry(),
                false,
                Language.LATIN
        ));
        bibExpected.add(new Bibliography(
                "Gaufman A S, Holtz R C, Meumer F J and Avramski J R 1975 Micromolecules 8 135-9",
                new StringSegment("Gaufman A S, Holtz R C, Meumer F J and Avramski J R 1975 Micromolecules 8 135-9", 0, 79),
                StringSegment.emptySegment(),
                StringSegment.emptySegment(),
                StringSegment.emptySegment(),
                new StringSegment("Micromolecules", 57, 71),
                new StringSegment("Gaufman A S, Holtz R C, Meumer F J and Avramski J R", 0, 51),
                new StringSegment("1975", 52, 56),
                new StringSegment("8", 72, 73),
                StringSegment.emptySegment(),
                StringSegment.emptySegment(),
                new StringSegment("135-9", 74, 79),
                Arrays.asList(
                        new Author("Gaufman", Arrays.asList("A", "S")),
                        new Author("Holtz", Arrays.asList("R", "C")),
                        new Author("Meumer", Arrays.asList("F", "J")),
                        new Author("Avramski", Arrays.asList("J", "R"))
                ),
                new Entry("", "8"),
                Entry.emptyEntry(),
                Entry.emptyEntry(),
                new Entry("", "135-9"),
                false,
                Language.LATIN
        ));
        bibExpected.add(new Bibliography(
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

        final List<Bibliography> bibActual = analyzer.analyze(fileService.readFile("testPool.txt"));

        assertEquals(bibExpected, bibActual);
    }
}
