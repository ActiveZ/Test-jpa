package entities.tp3;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "LIVRE")
public class Livre {
    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "TITRE", length = 255, nullable = false)
    private String titre;

    @Column(name = "AUTEUR", length = 50, nullable = false)
    private String auteur;

    @Override
    public String toString() {
        return "titre: '" + titre + ", auteur: '" + auteur;
    }

    @ManyToMany
    @JoinTable(name = "COMPO",
            joinColumns = @JoinColumn(name = "ID_LIV", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "ID_EMP", referencedColumnName = "ID")
    )

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }
}