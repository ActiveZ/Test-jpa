import entities.miniprojet.Customer;

import javax.persistence.*;

import static utils.Utils.*;

public class mini_projet {
    public static void main(String[] args) {

        EntityManagerFactory emf = null;

        try {
            emf = Persistence.createEntityManagerFactory("pizza-pu");
            EntityManager em = emf.createEntityManager();

            //lister les pizzas
            listPizzas(em);

            //lister les boissons
            listDrinks(em);

            //lister les commandes
            listOrders(em);

            //créer un nouveau client
            EntityTransaction tx = em.getTransaction();
            tx.begin();
            Customer client = new Customer();
            client.setName("Mario");
            client.setAddress("Nantes");
            client.setPhone("06-11-22-33-44");
            em.persist(client);
            tx.commit();
            System.out.println("Nouveau client => " + client.toString());

            //mettre à jour les informations d'un client
            tx.begin();
            client.setName("Luigi");
            tx.commit();

            //créer une commande
            tx.begin();
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
