package entities.tp4;

import javax.persistence.Entity;
import java.time.LocalDateTime;

@Entity
public class Virement extends Operation{

    String beneficiaire;

    public Virement() {
    }

    public Virement(double montant, LocalDateTime date, AbstractCompte compte, String motif, String beneficiaire) {
        super(-montant, date, compte, motif); // compte = compte emetteur, donc on ote le montant de son compte
        this.beneficiaire = beneficiaire;
    }

    public String getBeneficiaire() {
        return beneficiaire;
    }

    public void setBeneficiaire(String beneficiaire) {
        this.beneficiaire = beneficiaire;
    }
}