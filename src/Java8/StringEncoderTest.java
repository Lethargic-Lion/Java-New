package Java8;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StringEncoderTest {

    @Test
    void testTypicalEncoding() {
        String input = "aaaaaeeeffffffffffnnfffffffnnnnnnnneeeeeekkkkkkkkkkkk";
        String expected = "5a3e10f2n7f8n6e12k";
        assertEquals(expected, StringEncoder.encodeString(input));
    }

    @Test
    void testEmptyString() {
        assertEquals("", StringEncoder.encodeString(""));
    }

    @Test
    void testNullInput() {
        assertEquals("", StringEncoder.encodeString(null));
    }

    @Test
    void testSingleCharacter() {
        assertEquals("1a", StringEncoder.encodeString("a"));
    }

    @Test
    void testNoRepeats() {
        assertEquals("1a1b1c1d", StringEncoder.encodeString("abcd"));
    }
}
