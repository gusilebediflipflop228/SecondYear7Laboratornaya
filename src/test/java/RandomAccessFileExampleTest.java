import classes.RandomAccessFileExample;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class RandomAccessFileExampleTest {

    private final String testFileName = "testIntegers.bin";

    @BeforeEach
    public void setUp() throws IOException {

        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(testFileName))) {
            dos.writeInt(10);
            dos.writeInt(20);
            dos.writeInt(30);
            dos.writeInt(40);
            dos.writeInt(50);
        }
    }


    @Test
    public void testReadIntegersFromValidPosition() throws IOException {
        int startPosition = 1;
        int count = 4;

        int[] result = RandomAccessFileExample.readIntegersFromPosition(testFileName, startPosition, count);
        int[] expected = {20, 30, 40, 50};

        assertArrayEquals(expected, result, "The integers read from the file do not match the expected values.");
    }


    @Test
    public void testReadIntegersWithInvalidPosition() {
        int startPosition = 10;
        int count = 1;

        Exception exception = assertThrows(IOException.class, () ->
                RandomAccessFileExample.readIntegersFromPosition(testFileName, startPosition, count)
        );

        assertEquals("Reached end of file before reading 1 integers.", exception.getMessage(),
                "The exception message does not match the expected value.");
    }

    @Test
    public void testReadIntegersFromEnd() throws IOException {
        int startPosition = 4;
        int count = 1;

        int[] result = RandomAccessFileExample.readIntegersFromPosition(testFileName, startPosition, count);
        int[] expected = {50};

        assertArrayEquals(expected, result, "The integers read from the file do not match the expected values.");
    }

    @Test
    public void testReadIntegersFromStart() throws IOException {
        int startPosition = 0;
        int count = 2;

        int[] result = RandomAccessFileExample.readIntegersFromPosition(testFileName, startPosition, count);
        int[] expected = {10, 20};

        assertArrayEquals(expected, result, "The integers read from the file do not match the expected values.");
    }



}