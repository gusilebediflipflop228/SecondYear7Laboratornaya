package classes;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class House implements Serializable{

    private String houseNumber;
    private String address;
    private Person headOfHouse;
    private List<Flat> flats;

    public House(String houseNumber, String address, Person headOfHouse, List<Flat> flats) {
        this.houseNumber = houseNumber;
        this.address = address;
        this.headOfHouse = headOfHouse;
        this.flats = flats;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Person getHeadOfHouse() {
        return headOfHouse;
    }

    public void setHeadOfHouse(Person headOfHouse) {
        this.headOfHouse = headOfHouse;
    }

    public List<Flat> getFlats() {
        return flats;
    }

    public void setFlats(List<Flat> flats) {
        this.flats = flats;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        House house = (House) o;
        return Objects.equals(houseNumber, house.houseNumber) && Objects.equals(address, house.address) && Objects.equals(headOfHouse, house.headOfHouse) && Objects.equals(flats, house.flats);
    }

    @Override
    public int hashCode() {
        return Objects.hash(houseNumber, address, headOfHouse, flats);
    }
}
