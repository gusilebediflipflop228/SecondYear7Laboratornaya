package classes;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Arrays;

public class RandomAccessFileExample {

    public static int[] readIntegersFromPosition(String fileName, int startPosition, int count) throws IOException {
        try (RandomAccessFile randomAccessFile = new RandomAccessFile(fileName, "r")) {
            // Calculate the position in the file (startPosition * 4 because each int is 4 bytes)
            long bytePosition = startPosition * 4L;
            randomAccessFile.seek(bytePosition);

            // Read the required number of integers
            int[] result = new int[count];
            for (int i = 0; i < count; i++) {
                if (randomAccessFile.getFilePointer() < randomAccessFile.length()) {
                    result[i] = randomAccessFile.readInt();
                } else {
                    throw new IOException("Reached end of file before reading " + count + " integers.");
                }
            }
            return result;
        }
    }

    public static void main(String[] args) {
        String fileName = "integers.bin"; // Specify the binary file name
        int startPosition = 2; // Specify the starting position (0-based index)
        int count = 3; // Number of integers to read

        try {
            // Read integers starting from the specified position
            int[] integers = readIntegersFromPosition(fileName, startPosition, count);
            System.out.println("Read integers: " + Arrays.toString(integers));
        } catch (IOException e) {
            System.err.println("Error reading integers from file: " + e.getMessage());
        }
    }
}