package entities;

import javax.persistence.*;

@Entity
@Table(name = "gender")
public class Gender {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_gender", nullable = false)
    private int id_gender;

    @Column(name = "gender_name", nullable = false, length = 45)
    private String gender_name;

    public Gender(){}

    @Override
    public boolean equals(Object o) {

        if (o == this) return true;
        if (!(o instanceof Gender)) {
            return false;
        }

        Gender gender = (Gender) o;

        return gender.id_gender == this.id_gender && gender.gender_name.equals(this.gender_name);
    }

    //Idea from effective Java : Item 9
    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + (int)id_gender;
        result = 31 * result + gender_name.hashCode();
        return result;
    }

    public int getId_gender() {
        return id_gender;
    }

    public void setId_gender(int id_gender) {
        this.id_gender = id_gender;
    }

    public String getGender_name() {
        return gender_name;
    }

    public void setGender_name(String gender_name) {
        this.gender_name = gender_name;
    }
}
