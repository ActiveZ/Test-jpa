package entities.tp4;

import javax.persistence.*;

@Entity
@DiscriminatorValue("livretA")
public class LivretA extends AbstractCompte {

    Double taux;

    public LivretA() {
    }

    public LivretA(String numero, Double solde, Double taux) {
        super(numero, solde);
        this.taux = taux;
    }


    public Double getTaux() {
        return taux;
    }

    public void setTaux(Double taux) {
        this.taux = taux;
    }

    @Override
    public String toString() {
        return "LivretA{" +
                "taux=" + taux +
                '}';
    }
}