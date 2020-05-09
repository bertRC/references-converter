package education.bert.rc.temp;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestHelloWorld {

    @Test
    public void testSayHello() {
        final String expected = "Hello World!!!";
        final String actual = HelloWorld.sayHello();

        assertEquals(expected, actual);
    }
}
