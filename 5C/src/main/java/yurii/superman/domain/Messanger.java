package yurii.superman.domain;

import yurii.superman.DTO.EntityInterface;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "messanger")
public class Messanger implements EntityInterface {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_messanger", nullable = false)
    private Long id;

    @Column(name = "messanger_name", nullable = false, length = 45)
    private String messangerName;

    @ManyToMany
    @JoinTable(name = "available",
            joinColumns = @JoinColumn(name = "id_messanger", referencedColumnName = "id_messanger", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "id_person", referencedColumnName = "id_person", nullable = false))
    private Set<Person> persons;

    public Messanger(){}

    public Messanger(String messangerName) {
        this.messangerName = messangerName;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessangerName() {
        return messangerName;
    }

    public void setMessangerName(String messangerName) {
        this.messangerName = messangerName;
    }

    public Set<Person> getPersons() {
        return persons;
    }

    public void setPersons(Set<Person> persons) {
        this.persons = persons;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Messanger)) return false;
        Messanger messanger = (Messanger) o;
        return Objects.equals(id, messanger.id) &&
                Objects.equals(messangerName, messanger.messangerName) &&
                Objects.equals(persons, messanger.persons);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, messangerName, persons);
    }
}
