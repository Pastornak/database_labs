package entities;

import javax.persistence.*;

@Entity
@Table(name = "messanger", schema = "pasternak_5")
public class Messanger {

    private int id_messanger;
    private String messanger_name;

    public Messanger(){}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_messanger", nullable = false)
    public int getId_messanger() { return id_messanger; }

    public void setId_messanger(int id_messanger) {
        this.id_messanger = id_messanger;
    }

    @Column(name = "messanger_name", nullable = false, length = 45)
    public String getMessanger_name() {
        return messanger_name;
    }

    public void setMessanger_name(String messanger_name) {
        this.messanger_name = messanger_name;
    }
}
