package entities.tp3;

import javax.persistence.*;
import java.time.Instant;
import java.util.Set;

@Entity
@Table(name = "EMPRUNT")
public class Emprunt {
    @Id
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Column(name = "DATE_DEBUT", nullable = false)
    private Instant dateDebut;

    @Column(name = "DATE_FIN")
    private Instant dateFin;

    @Column(name = "DELAI")
    private Integer delai;

    @ManyToOne
    @JoinColumn(name = "ID_CLIENT")
    private Client_tp3 client;

    @Override
    public String toString() {
        return  "date début = " + dateDebut +
                ", date Fin = " + dateFin +
                ", délai = " + delai +
                ", client = " + client.getNom() + " " + client.getPrenom() + "\n";
    }

    public Set<Livre> getLivres() {
        return Livres;
    }

    public void setLivres(Set<Livre> livres) {
        Livres = livres;
    }

    @ManyToMany
    @JoinTable(name = "COMPO",
            joinColumns = @JoinColumn(name = "ID_EMP", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "ID_LIV", referencedColumnName = "ID")
    )
    private Set<Livre> Livres;



    public Integer getDelai() {
        return delai;
    }

    public void setDelai(Integer delai) {
        this.delai = delai;
    }

    public Instant getDateFin() {
        return dateFin;
    }

    public void setDateFin(Instant dateFin) {
        this.dateFin = dateFin;
    }

    public Instant getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Instant dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}