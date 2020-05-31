package education.bert.rc.utils.misc;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
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
}
