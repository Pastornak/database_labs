package entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "available", schema = "pasternak_5")
public class Available implements Serializable{

    @EmbeddedId
    private AvailablePK pk;

    public Available(){}

    @Override
    public boolean equals(Object o) {
        return pk.equals(o);
    }

    @Override
    public int hashCode() {
        return pk.hashCode();
    }

    public void setAvailablePK(AvailablePK availablePK){
        this.pk = availablePK;
    }

    public int getPerson() {
        return pk.getId_person();
    }

    public void setPerson(int person) {
        this.pk.setId_person(person);
    }

    public int getMessanger() {
        return pk.getId_messanger();
    }

    public void setMessanger(int messanger) {
        this.pk.setId_messanger(messanger);
    }
}
