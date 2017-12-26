package yurii.superman.domain;

import yurii.superman.DTO.EntityInterface;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "gender")
public class Gender implements EntityInterface {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_gender", nullable = false)
    private Long id;

    @Column(name = "gender_name", nullable = false, length = 45)
    private String genderName;

    @OneToMany(mappedBy = "gender")
    private List<Person> persons;

    public Gender(){}

    public Gender(String genderName){
        this.genderName = genderName;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGenderName() {
        return genderName;
    }

    public void setGenderName(String genderName) {
        this.genderName = genderName;
    }

    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }

    @Override
    public boolean equals(Object o) {

        if (o == this) return true;
        if (!(o instanceof Gender)) {
            return false;
        }

        Gender gender = (Gender) o;

        return gender.id.equals(this.id) && gender.genderName.equals(this.genderName);
    }

    //Idea from effective Java : Item 9
    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + id.intValue();
        result = 31 * result + genderName.hashCode();
        return result;
    }
}
