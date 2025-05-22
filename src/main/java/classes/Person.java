package classes;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class Person implements Serializable {

    private String lastname;
    private String firstname;
    private String surname;
    private String birthday;
    public Person() {
        // Конструктор по умолчанию
    }

    public Person(String lastname, String firstname, String surname, String birthday) {
        this.lastname = lastname;
        this.firstname = firstname;
        this.surname = surname;
        this.birthday = String.valueOf(birthday);
    }

    public String getLastname() {
        return lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getSurname() {
        return surname;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setBirthday(String birthday) {
        this.birthday = String.valueOf(LocalDate.parse(birthday));
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(lastname, person.lastname) && Objects.equals(firstname, person.firstname) && Objects.equals(surname, person.surname) && Objects.equals(birthday, person.birthday);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lastname, firstname, surname, birthday);
    }
}
