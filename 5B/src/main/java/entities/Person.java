package entities;

import javax.persistence.*;

@Entity
@Table(name = "person")
public class Person {

    private int id_person;
    private String name;
    private String surname;
    private Gender gender;

    public Person(){}

    @Override
    public boolean equals(Object o) {

        if (o == this) return true;
        if (!(o instanceof Person)) {
            return false;
        }

        Person person = (Person) o;

        return person.id_person == this.id_person && person.name.equals(this.name) && person.surname.equals(this.surname)
                && person.gender.equals(this.gender);
    }

    //Idea from effective Java : Item 9
    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + (int)id_person;
        result = 31 * result + name.hashCode();
        result = 31 * result + surname.hashCode();
        result = 31 * result + gender.hashCode();
        return result;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_person", nullable = false)
    public int getId_person() {
        return id_person;
    }

    public void setId_person(int id_person) {
        this.id_person = id_person;
    }

    @Column(name = "name", nullable = false, length = 45)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "surname", nullable = false, length = 45)
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @ManyToOne
    @JoinColumn(name = "id_gender")
    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }
}
