package classes;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class Flat implements Serializable {

    private int number;
    private double square;
    private List<Person> owners;

    public Flat() {
        // Конструктор по умолчанию
    }
    public Flat(int number, double square, List<Person> owners) {
        this.number = number;
        this.square = square;
        this.owners = owners;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public double getSquare() {
        return square;
    }

    public void setSquare(double square) {
        this.square = square;
    }

    public List<Person> getOwners() {
        return owners;
    }

    public void setOwners(List<Person> owners) {
        this.owners = owners;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Flat flat = (Flat) o;
        return number == flat.number && Double.compare(square, flat.square) == 0 && Objects.equals(owners, flat.owners);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, square, owners);
    }
}
