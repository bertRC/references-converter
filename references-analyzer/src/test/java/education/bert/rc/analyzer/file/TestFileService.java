package education.bert.rc.analyzer.file;

import education.bert.rc.analyzer.file.exception.FileAccessException;
import education.bert.rc.analyzer.file.service.FileService;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestFileService {

    private final FileService fileService = new FileService("testUpload");

    @Test
    public void testReadFile() {
        final List<String> expectedList = new ArrayList<>();
        expectedList.add("Line 1");
        expectedList.add("Line 2");
        expectedList.add("Line 3");

        final List<String> actualList = fileService.readFile("testFile.txt");

        assertEquals(expectedList, actualList);
    }

    @Test
    public void testWrongUploadPath() {
        assertThrows(FileAccessException.class, () -> new FileService("X:\\test"));
    }

    @Test
    public void testWrongFile() {
        assertThrows(FileAccessException.class, () -> fileService.readFile("none"));
    }
}
