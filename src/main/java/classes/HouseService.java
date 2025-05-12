package classes;

import java.io.*;

public class HouseService {

    public static void serializeHouse(House house, OutputStream outputStream) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(outputStream)) {
            oos.writeObject(house);
        }
    }

    public static House deserializeHouse(InputStream inputStream) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(inputStream)) {
            return (House) ois.readObject();
        }
    }

}
