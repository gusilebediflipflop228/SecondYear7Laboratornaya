import classes.BinaryAndCharacterStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.*;

import static classes.BinaryAndCharacterStream.writeArrayToBinaryFile;
import static org.junit.jupiter.api.Assertions.*;

public class BinaryAndCharacterStreamTest {

    private final String testFileName = "testArrayData.bin";

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
    public void testWriteArrayToCharacterFileWithValidArray() throws IOException {
        int[] testArray = {10, 20, 30, 40, 50};
        BinaryAndCharacterStream.writeArrayToCharacterFile(testArray, testFileName);

        try (BufferedReader reader = new BufferedReader(new FileReader(testFileName))) {
            String line = reader.readLine();
            assertNotNull(line, "File should not be empty.");
            assertEquals("10 20 30 40 50", line, "File contents do not match the expected string.");
        }
    }

    @Test
    public void testWriteArrayToCharacterFileWithEmptyArray() throws IOException {
        int[] testArray = {};
        BinaryAndCharacterStream.writeArrayToCharacterFile(testArray, testFileName);

        try (BufferedReader reader = new BufferedReader(new FileReader(testFileName))) {
            String line = reader.readLine();
            assertNull(line, "File should be empty for an empty array.");
        }
    }

    @Test
    public void testWriteArrayToCharacterFileWithLargeArray() throws IOException {
        int[] testArray = new int[1000];
        StringBuilder expectedString = new StringBuilder();
        for (int i = 0; i < testArray.length; i++) {
            testArray[i] = i;
            expectedString.append(i);
            if (i < testArray.length - 1) {
                expectedString.append(" ");
            }
        }

        BinaryAndCharacterStream.writeArrayToCharacterFile(testArray, testFileName);

        try (BufferedReader reader = new BufferedReader(new FileReader(testFileName))) {
            String line = reader.readLine();
            assertNotNull(line, "File should not be empty.");
            assertEquals(expectedString.toString(), line, "File contents do not match the expected large array string.");
        }
    }




    private void writeTestDataToFile(String content) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(testFileName))) {
            writer.write(content);
        }
    }

    @Test
    public void testReadArrayFromCharacterFileWithValidData() throws IOException {
        String fileContent = "10 20 30 40 50";
        writeTestDataToFile(fileContent);

        int[] resultArray = BinaryAndCharacterStream.readArrayFromCharacterFile(testFileName);

        int[] expectedArray = {10, 20, 30, 40, 50};
        assertArrayEquals(expectedArray, resultArray, "The read array does not match the original array.");
    }

    @Test
    public void testReadArrayFromCharacterFileWithEmptyFile() throws IOException {
        writeTestDataToFile("");

        int[] resultArray = BinaryAndCharacterStream.readArrayFromCharacterFile(testFileName);

        int[] expectedArray = {};
        assertArrayEquals(expectedArray, resultArray, "The read array should be empty for an empty file.");
    }

    @Test
    public void testReadArrayFromCharacterFileWithLargeArray() throws IOException {
        StringBuilder fileContent = new StringBuilder();
        int[] expectedArray = new int[1000];
        for (int i = 0; i < 1000; i++) {
            expectedArray[i] = i;
            fileContent.append(i);
            if (i < 999) {
                fileContent.append(" ");
            }
        }
        writeTestDataToFile(fileContent.toString());

        int[] resultArray = BinaryAndCharacterStream.readArrayFromCharacterFile(testFileName);

        assertArrayEquals(expectedArray, resultArray, "The read array does not match the large array.");
    }




}

