package classes;

import java.io.*;

public class BinaryAndCharacterStream {

    public static void writeArrayToBinaryFile(int[] array, String fileName) throws IOException {
        try (DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream(fileName))) {
            dataOutputStream.writeInt(array.length);
            for (int num : array) {
                dataOutputStream.writeInt(num);
            }
        }
    }

    public static int[] readArrayFromBinaryFile(String fileName) throws IOException {
        try (DataInputStream dataInputStream = new DataInputStream(new FileInputStream(fileName))) {
            int length = dataInputStream.readInt();
            int[] array = new int[length];
            for (int i = 0; i < length; i++) {
                array[i] = dataInputStream.readInt();
            }
            return array;
        }
    }

    public static void writeArrayToCharacterFile(int[] array, String fileName) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (int i = 0; i < array.length; i++) {
                writer.write(String.valueOf(array[i]));
                if (i < array.length - 1) {
                    writer.write(" ");
                }
            }
        }
    }

    public static int[] readArrayFromCharacterFile(String fileName) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line = reader.readLine();
            if (line != null && !line.isEmpty()) {
                String[] numberStrings = line.split(" ");
                int[] array = new int[numberStrings.length];
                for (int i = 0; i < numberStrings.length; i++) {
                    array[i] = Integer.parseInt(numberStrings[i]);
                }
                return array;
            }
            return new int[0];
        }
    }

}
