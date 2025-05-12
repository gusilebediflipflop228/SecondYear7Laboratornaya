import classes.BinaryAndCharacterStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.file.Files;

import static classes.BinaryAndCharacterStream.writeArrayToBinaryFile;
import static org.junit.jupiter.api.Assertions.*;

public class BinaryAndCharacterStreamTest {

    private final String testFileName = "file3.txt";
    private File testFile;

    @BeforeEach
    public void setUp(){
        testFile = new File(testFileName);}

    @AfterEach
    public void cleanUp() {
        File file = new File(testFileName);
        if (file.exists()) {
            file.delete();
        }
    }

    @Test
    public void testWriteArrayToBinaryFileWithValidArray() throws IOException {
        int[] testArray = {10, 20, 30, 40, 50};
        writeArrayToBinaryFile(testArray, testFileName);

        try (DataInputStream dataInputStream = new DataInputStream(new FileInputStream(testFileName))) {
            int length = dataInputStream.readInt();
            assertEquals(testArray.length, length, "Array length in file does not match.");

            for (int i = 0; i < testArray.length; i++) {
                assertEquals(testArray[i], dataInputStream.readInt(), "Array element mismatch at index " + i);
            }
        }
    }

    @Test
    public void testWriteArrayToBinaryFileWithEmptyArray() throws IOException {
        int[] testArray = {};
        writeArrayToBinaryFile(testArray, testFileName);

        try (DataInputStream dataInputStream = new DataInputStream(new FileInputStream(testFileName))) {
            int length = dataInputStream.readInt();
            assertEquals(0, length, "Array length in file should be 0 for an empty array.");
        }
    }

    @Test
    public void testWriteArrayToBinaryFileWithLargeArray() throws IOException {
        int[] testArray = new int[1000];
        for (int i = 0; i < testArray.length; i++) {
            testArray[i] = i;
        }

        writeArrayToBinaryFile(testArray, testFileName);

        try (DataInputStream dataInputStream = new DataInputStream(new FileInputStream(testFileName))) {
            int length = dataInputStream.readInt();
            assertEquals(testArray.length, length, "Array length in file does not match.");

            for (int i = 0; i < testArray.length; i++) {
                assertEquals(testArray[i], dataInputStream.readInt(), "Array element mismatch at index " + i);
            }
        }
    }


    private void writeTestArrayToBinaryFile(int[] array) throws IOException {
        try (DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream(testFileName))) {
            dataOutputStream.writeInt(array.length);
            for (int num : array) {
                dataOutputStream.writeInt(num);
            }
        }
    }

    @Test
    public void testReadArrayFromBinaryFileWithValidData() throws IOException {
        int[] testArray = {10, 20, 30, 40, 50};
        writeTestArrayToBinaryFile(testArray);

        int[] resultArray = BinaryAndCharacterStream.readArrayFromBinaryFile(testFileName);

        assertArrayEquals(testArray, resultArray, "The read array does not match the original array.");
    }

    @Test
    public void testReadArrayFromBinaryFileWithEmptyArray() throws IOException {
        int[] testArray = {};

        writeTestArrayToBinaryFile(testArray);

        int[] resultArray = BinaryAndCharacterStream.readArrayFromBinaryFile(testFileName);

        assertArrayEquals(testArray, resultArray, "The read array should be empty.");
    }

    @Test
    public void testReadArrayFromBinaryFileWithLargeArray() throws IOException {
        int[] testArray = new int[1000];
        for (int i = 0; i < testArray.length; i++) {
            testArray[i] = i;
        }
        writeTestArrayToBinaryFile(testArray);

        int[] resultArray = BinaryAndCharacterStream.readArrayFromBinaryFile(testFileName);

        assertArrayEquals(testArray, resultArray, "The read array does not match the large array.");
    }





    @Test
    public void testWriteArrayToCharacterFileWithValidData() throws IOException {
        int[] items = {1, 2, 3, 4, 5};

        BinaryAndCharacterStream.writeArrayToCharacterFile(items);

        assertTrue(testFile.exists(), "The file should be created.");
        try (BufferedReader reader = new BufferedReader(new FileReader(testFile))) {
            String content = reader.readLine();
            assertEquals("1 2 3 4 5 ", content, "The file content should match the array elements separated by spaces.");
        }
    }

    @Test
    public void testWriteArrayToCharacterFileWithEmptyArray() throws IOException {
        int[] items = {};


        BinaryAndCharacterStream.writeArrayToCharacterFile(items);

        assertTrue(testFile.exists(), "The file should be created.");
        try (BufferedReader reader = new BufferedReader(new FileReader(testFile))) {
            String content = reader.readLine();
            assertNull(content, "The file should be empty for an empty array.");
        }
    }

    @Test
    public void testWriteArrayToCharacterFileWithLargeArray() throws IOException {
        int[] items = new int[1000];
        for (int i = 0; i < items.length; i++) {
            items[i] = i + 1;
        }

        BinaryAndCharacterStream.writeArrayToCharacterFile(items);

        assertTrue(testFile.exists(), "The file should be created.");
        try (BufferedReader reader = new BufferedReader(new FileReader(testFile))) {
            StringBuilder expectedContent = new StringBuilder();
            for (int i = 1; i <= 1000; i++) {
                expectedContent.append(i).append(" ");
            }
            String content = reader.readLine();
            assertEquals(expectedContent.toString(), content, "The file content should match the large array elements separated by spaces.");
        }
    }

    @Test
    public void testReadArrayFromCharacterFileWithValidData() throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(testFile))) {
            writer.write("1 2 3 4 5");
        }

        int[] result = BinaryAndCharacterStream.readArrayFromCharacterFile(5);

        int[] expected = {1, 2, 3, 4, 5};
        assertArrayEquals(expected, result, "The read array should match the expected values.");
    }

    @Test
    public void testReadArrayFromCharacterFileWithInvalidData() throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(testFile))) {
            writer.write("1 2 a 4 5");
        }

        assertThrows(NumberFormatException.class, () ->
                        BinaryAndCharacterStream.readArrayFromCharacterFile(5), "Reading invalid data should throw a NumberFormatException.");
    }





}

