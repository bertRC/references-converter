package education.bert.rc.utils.misc;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestSeparator {

    @Test
    public void testSeparate() {
        String text = "qwe\nrty";
        List<String> expected = Arrays.asList("qwe", "rty");
        assertEquals(expected, Separator.separate(text));
    }

    @Test
    public void testGetLineSeparator() {
        String text = "qwe\nrty";
        assertEquals("\n", Separator.getLineSeparator(text));
    }

    @Test
    public void testGetDefaultLineSeparator() {
        String text = "qwerty";
        assertEquals("\r\n", Separator.getLineSeparator(text));
    }

    @Test
    public void testJoinOneLine() {
        String expected = "line1";
        List<String> strings = Collections.singletonList("line1");
        assertEquals(expected, Separator.join(strings, "\r\n"));
    }

    @Test void testJoin() {
        String expected = "line1\r\nline2\r\nline3";
        List<String> strings = Arrays.asList("line1<br>line2", "line3");
        assertEquals(expected, Separator.join(strings, "\r\n"));
    }
}
