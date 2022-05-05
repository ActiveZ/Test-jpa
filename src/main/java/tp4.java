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
            System.out.println("\nCréation de la banque: " + banque.toString() + "\n");

            /**
             * client 1
             */
            // creation du client 1
            Client client1 = new Client("Dupontel", "Albert", LocalDate.of(1789, 7, 14));
            Adresse adresse1 = new Adresse(1, "rue de la Bastille", 75000, "Paris");
            client1.setAdresse(adresse1);
            client1.setBanque(banque);
            em.persist(client1);
            System.out.println("Client 1 ajouté: " + client1.toString());

            // création d'un livret A pour le client 1
            LivretA livretA1 = new LivretA("xxx-xxx", 0.0, 1.5);
            em.persist(livretA1);
            client1.setComptes(livretA1);
            System.out.println("Compte livret A créé pour client 1: " + livretA1.toString());

            // création d'une assurance vie pour le client 1
            AssuranceVie assuranceVie1 = new AssuranceVie("yyy-yyy", 0.0, 3.0, LocalDate.of(2030, 12, 31));
            em.persist(assuranceVie1);
            client1.setComptes(assuranceVie1);
            System.out.println("Compte assurance vie créé pour client 1: " + assuranceVie1.toString());

            // affichage de la liste des comptes du client 1
            System.out.println("Comptes du client 1: " + client1.getComptes() + "\n");

            /**
             * client 2
             */
            // creation du client 2
            Client client2 = new Client("Enfaillite", "Mélusine", LocalDate.of(2000, 1, 1));
            Adresse adresse2 = new Adresse(1, "rue du Bug", 29100, "Douarnenez");
            client2.setAdresse(adresse2);
            client2.setBanque(banque);
            em.persist(client2);
            System.out.println("Client 2 ajouté: " + client2.toString());

            // création d'un livret A pour le client 2
            LivretA livretA2 = new LivretA("AAA-AAA", 0.0, 1.5);
            em.persist(livretA2);
            client2.setComptes(livretA2);
            System.out.println("Compte livret A créé pour client 2: " + livretA2.toString());

            // création d'une assurance vie pour le client 1
            AssuranceVie assuranceVie2 = new AssuranceVie("BBB-BBB", 0.0, 3.0, LocalDate.of(2030, 12, 31));
            em.persist(assuranceVie2);
            client2.setComptes(assuranceVie2);
            System.out.println("Compte assurance vie créé pour client 2: " + assuranceVie2.toString());

            // affichage de la liste des comptes du client 2
            System.out.println("Comptes du client 2: " + client2.getComptes() + "\n");

            /**
             * opérations
             */
            // ajout d'une opération de crédit sur le compte livret A du client 1
            Operation op1 = new Operation(3000, LocalDateTime.now(), client1.getComptes().get(0), "parce que !");
            em.persist(op1);
            System.out.println("ajout d'une opération sur le compte livret A du client 1: " + op1.toString());

            // affichage des soldes des comptes avant opération virement 500 client 1 -> client 2
            System.out.println("soldes des comptes avant opération: client 1: " + client1.getComptes().get(0).getSolde());
            System.out.println("soldes des comptes avant opération: client 2: " + client2.getComptes().get(0).getSolde());

            // virement client 1 -> client 2 montant: 500
            Double montant = 500.0;
            Virement vt = new Virement(
                    montant,
                    LocalDateTime.now(),
                    client1.getComptes().get(0),
                    "Virement vers client 2",
                    client2.getNom() + " " + client2.getPrenom());
            em.persist(vt);
            // crédit sur le compte destinataire
            Operation op3 = new Operation(montant, LocalDateTime.now(), client2.getComptes().get(0), "vt de client 1");
            em.persist(op3);

            // affichage des soldes des comptes après opération virement 500 client 1 -> client 2
            System.out.println("soldes des comptes après opération: client 1: " + client1.getComptes().get(0).getSolde());
            System.out.println("soldes des comptes après opération: client 2: " + client2.getComptes().get(0).getSolde());

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
