import entities.tp4.Banque;
import entities.tp4.Client;
import entities.tp4.Compte;

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
            Banque banque = new Banque();
            banque.setNom("Société Géniale");
            em.persist(banque);

            // creation d'un client
            Client client = new Client();
            client.setNom("Dupontel");
            client.setPrenom("Albert");
            client.setDateNaissance(LocalDate.of(1789, 7, 14));
            client.setBanque(banque);
            em.persist(client);

            // creation d'un compte
            Compte compte = new Compte();
            compte.addClient(client);
            em.persist(compte);

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
