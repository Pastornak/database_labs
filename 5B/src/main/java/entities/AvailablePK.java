package entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class AvailablePK implements Serializable{
    @Column(name = "id_person")
    private int id_person;

    @Column(name = "id_messanger")
    private int id_messanger;

    public AvailablePK() {
    }

    public AvailablePK(int id_person, int id_messanger) {
        this.id_person = id_person;
        this.id_messanger = id_messanger;
    }

    @Override
    public boolean equals(Object o) {

        if (o == this) return true;
        if (!(o instanceof Person)) {
            return false;
        }

        AvailablePK pk = (AvailablePK) o;

        return pk.id_person == this.id_person && pk.id_messanger == this.id_messanger;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + this.id_person;
        result = 31 * result + this.id_messanger;
        return result;
    }

    public int getId_person() {
        return id_person;
    }

    public void setId_person(int id_person) {
        this.id_person = id_person;
    }

    public int getId_messanger() {
        return id_messanger;
    }

    public void setId_messanger(int id_messanger) {
        this.id_messanger = id_messanger;
    }
}