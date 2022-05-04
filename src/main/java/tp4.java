import entities.tp4.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

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

            // création d'un livret A
            LivretA livretA = new LivretA("xxx-xxx", 0.0, 1.5);
            em.persist(livretA);
            client.setComptes(livretA);

            // création d'une assurance vie
            AssuranceVie assuranceVie = new AssuranceVie("yyy-yyy", 0.0, 3.0, LocalDate.of(2030, 12, 31));
            em.persist(assuranceVie);
            client.setComptes(assuranceVie);

            // ajout d'une opération
//            System.out.println("get1 = " + client.getComptes().get(0).getId());
            Operation op = new Operation(1000, LocalDateTime.now(), client.getComptes().get(0), "parce que !");
            em.persist(op);

            tx.commit();
            em.close();
        } catch (PersistenceException e) {
            System.err.println("Erreur de persistance : " + e.getMessage());
        } finally {
            if (emf != null) {
                emf.close();
            }
        }
    }
}
