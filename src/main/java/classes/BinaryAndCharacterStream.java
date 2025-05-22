package classes;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

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

    public static void writeArrayToCharacterFile(int[] items){
        try(FileWriter dos = new FileWriter("file3.txt")) {
            for(Integer item : items){
                dos.write(String.valueOf(item));
                dos.write(' ');

            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static int[] readArrayFromCharacterFile(int n) {
        int[] result = new int[n];
        String[] numbers;
        try (BufferedReader dos = new BufferedReader(Files.newBufferedReader(Paths.get("file3.txt")))) {
            numbers = dos.readLine().split(" ");
            for (int i = 0; i< numbers.length; i++){
                result[i] = Integer.parseInt(numbers[i]);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

}
