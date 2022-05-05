package entities.tp4;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "operation")
public class Operation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "montant", nullable = false)
    private double montant;

    @Column(name = "date", nullable = false)
    private LocalDateTime date;

    @Column(name = "motif", nullable = false)
    private String motif;

    @ManyToOne
    @JoinColumn(name = "idCompte")
    private AbstractCompte compte;

    public Operation() {
    }

    public Operation(double montant, LocalDateTime date, AbstractCompte compte, String motif) {
        this.montant = montant;
        this.date = date;
        this.compte = compte;
        this.motif = motif;
        // update solde du compte
        compte.setSolde(compte.getSolde() + montant);
    }

    public AbstractCompte getCompte() {
        return compte;
    }

    public void setCompte(AbstractCompte compte) {
        this.compte = compte;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public String getMotif() {
        return motif;
    }

    public void setMotif(String motif) {
        this.motif = motif;
    }

    @Override
    public String toString() {
        return "Operation{" +
                "id=" + id +
                ", montant=" + montant +
                ", date=" + date +
                ", motif='" + motif + '\'' +
                ", compte=" + compte +
                '}';
    }
}