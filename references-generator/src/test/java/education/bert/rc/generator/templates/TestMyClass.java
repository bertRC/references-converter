package education.bert.rc.generator.templates;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestMyClass {

    @Test
    public void test() {
        final MyClass myClass = new MyClass("Alb", "ert");
        assertEquals("Albert", myClass.concat());
    }
}
