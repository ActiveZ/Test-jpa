import entities.tp4.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.time.LocalDate;

public class tp4 {
    public static void main(String[] args) {
        EntityManagerFactory emf = null;

        try {
            emf = Persistence.createEntityManagerFactory("banque");
            EntityManager em = emf.createEntityManager();
            EntityTransaction tx = em.getTransaction();
            tx.begin();

            // creation d'une banque
            Banque banque = new Banque("Société Géniale");
            em.persist(banque);

            // creation d'un client
            Client client = new Client("Dupontel", "Albert", LocalDate.of(1789, 7, 14));
            Adresse adresse = new Adresse(1, "rue de la Bastille", 75000, "Paris");
            client.setAdresse(adresse);
            client.setBanque(banque);
            em.persist(client);

            // ccréation d'un livret A
            LivretA livretA = new LivretA(1.5);
            em.persist(livretA);
            client.setComptes(livretA);

            // création d'une assurance vie
            AssuranceVie assuranceVie = new AssuranceVie(3.0, LocalDate.of(2030, 12, 31));
            em.persist(assuranceVie);
            client.setComptes(assuranceVie);

            tx.commit();
            em.close();
        }
//        catch (PersistenceException e) {
//            System.err.println("Erreur de persistance : " + e.getMessage());
        finally {
            if (emf != null) {
                emf.close();
            }
        }
    }
}
