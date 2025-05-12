package classes;

import java.io.IOException;
import java.io.RandomAccessFile;

public class RandomAccessFileExample {

    public static int[] readIntegersFromPosition(String fileName, int startPosition, int count) throws IOException {
        try (RandomAccessFile randomAccessFile = new RandomAccessFile(fileName, "r")) {
            long bytePosition = startPosition * 4L;
            randomAccessFile.seek(bytePosition);

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

}