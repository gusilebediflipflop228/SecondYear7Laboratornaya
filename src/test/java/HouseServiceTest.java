import classes.Flat;
import classes.House;
import classes.HouseService;
import classes.Person;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class HouseServiceTest {

    private File temporaryFile;

    @BeforeEach
    public void setUp() throws IOException {
        temporaryFile = File.createTempFile("house", ".ser");
    }

    @AfterEach
    public void tearDown() {
        if (temporaryFile.exists()) {
            temporaryFile.delete();
        }
    }

    @Test
    public void testSerializeHouseWithValidHouse() throws IOException {
        Person headOfHouse = new Person("John", "Doe", "Middle", LocalDate.of(1980, 5, 20));
        Person owner1 = new Person("Alice", "Smith", "Maria", LocalDate.of(1995, 8, 15));
        Person owner2 = new Person("Bob", "Brown", "James", LocalDate.of(1990, 12, 10));

        Flat flat1 = new Flat(1, 50.5, List.of(owner1));
        Flat flat2 = new Flat(2, 70.0, List.of(owner2));
        House house = new House("123456", "123 Main St", headOfHouse, Arrays.asList(flat1, flat2));

        try (FileOutputStream fos = new FileOutputStream(temporaryFile)) {
            HouseService.serializeHouse(house, fos);
        }
    }

    @Test
    public void testSerializeHouseWithEmptyHouse() throws IOException {
        Person headOfHouse = new Person("John", "Doe", "Middle", LocalDate.of(1980, 5, 20));
        House house = new House("654321", "456 Another St", headOfHouse, List.of());

        try (FileOutputStream fos = new FileOutputStream(temporaryFile)) {
            HouseService.serializeHouse(house, fos);
        }

        assertTrue(temporaryFile.length() > 0, "The serialized file should not be empty.");
    }

    @Test
    public void testSerializeHouseWithNullStream() {
        Person headOfHouse = new Person("John", "Doe", "Middle", LocalDate.of(1980, 5, 20));
        House house = new House("789012", "789 Empty St", headOfHouse, List.of());

        assertThrows(NullPointerException.class, () ->
                        HouseService.serializeHouse(house, null), "Serializing with a null stream should throw a NullPointerException.");
    }



    @Test
    public void testDeserializeHouseWithNullStream() {
        assertThrows(NullPointerException.class, () ->
                        HouseService.deserializeHouse(null), "Deserializing with a null stream should throw a NullPointerException.");
    }

    @Test
    public void testDeserializeHouseWithEmptyFile() throws IOException {
        try (FileWriter writer = new FileWriter(temporaryFile)) {
            writer.write("");
        }

        try (FileInputStream fis = new FileInputStream(temporaryFile)) {
            assertThrows(EOFException.class, () -> HouseService.deserializeHouse(fis), "Deserializing from an empty file should throw an EOFException.");
        }
    }



}