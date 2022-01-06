import entities.tp3.Client;
import entities.tp3.Emprunt;

import javax.persistence.*;

public class tp3 {
    public static void main(String[] args) {
        EntityManagerFactory emf = null;

        try {
            emf = Persistence.createEntityManagerFactory("biblio-pu");
            EntityManager em = emf.createEntityManager();

//        Réalisez une requête qui permet d’extraire un emprunt et tous ses livres associés.
            Emprunt emprunt = em.find(Emprunt.class, 1);
            System.out.println("Emprunt id=1 => " + emprunt.toString());

//        Réalisez une requête qui permet d’extraire tous les emprunts d’un client donné.
            int idClient = 1;
            Client client = em.find(Client.class, idClient);
            System.out.println("Le client " + client.getNom() + " " + client.getPrenom() + " a emprunté:");
            client.getEmprunts().forEach(e -> {
                e.getLivres().forEach(livre -> System.out.println(livre.toString()));
            });
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
