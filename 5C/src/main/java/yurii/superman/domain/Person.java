package yurii.superman.domain;

import yurii.superman.DTO.EntityInterface;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "person")
public class Person implements EntityInterface {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_person", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false, length = 45)
    private String name;

    @Column(name = "surname", nullable = false, length = 45)
    private String surname;

    @ManyToOne
    @JoinColumn(name = "id_gender", referencedColumnName = "id_gender")
    private Gender gender;

    @ManyToMany
    @JoinTable(name = "available",
            joinColumns = @JoinColumn(name = "id_person", referencedColumnName = "id_person", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "id_messanger", referencedColumnName = "id_messanger", nullable = false))
    private Set<Messanger> messangers;

    Person(){}

    public Person(String name, String surname, Gender gender) {
        this.name = name;
        this.surname = surname;
        this.gender = gender;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Set<Messanger> getMessangers() {
        return messangers;
    }

    public void setMessangers(Set<Messanger> messangers) {
        this.messangers = messangers;
    }

    @Override
    public boolean equals(Object o) {

        if (o == this) return true;
        if (!(o instanceof Person)) {
            return false;
        }

        Person person = (Person) o;

        return person.id.equals(this.id) && person.name.equals(this.name) && person.surname.equals(this.surname)
                && person.gender.equals(this.gender);
    }

    //Idea from effective Java : Item 9
    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + id.intValue();
        result = 31 * result + name.hashCode();
        result = 31 * result + surname.hashCode();
        result = 31 * result + gender.hashCode();
        return result;
    }
}
