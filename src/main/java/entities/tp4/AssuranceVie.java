package entities.tp4;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
@DiscriminatorValue("assVie")
public class AssuranceVie extends AbstractCompte {

    Double taux;
    LocalDate dateFin;

    public AssuranceVie() {
    }

    public AssuranceVie(Double taux, LocalDate dateFin) {
        this.taux = taux;
        this.dateFin = dateFin;
    }

    public Double getTaux() {
        return taux;
    }

    public void setTaux(Double taux) {
        this.taux = taux;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }
}