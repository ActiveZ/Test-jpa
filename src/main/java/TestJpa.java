import entities.Livre;
import entities.Pizza;

import javax.persistence.*;
import java.util.List;

public class TestJpa {
    public static void main(String[] args) {

        EntityManagerFactory emf = null;

        //////////// TP 01 //////////////
//        try {
//            emf = Persistence.createEntityManagerFactory("pizza-pu");
//            EntityManager em = emf.createEntityManager();
//            Pizza pizzaId1 = em.find(Pizza.class, 1);
//            System.out.println("Pizza id=1 name=" + pizzaId1.getName());
//            TypedQuery<Pizza> query = em.createQuery("select p from Pizza p", Pizza.class);
//            List<Pizza> listePizzas = query.getResultList();
//            for (Pizza pizza : listePizzas) {
//                System.out.println(pizza.getName());
//            }
//            em.close();
//            System.out.println("Pizza id=1 name=" + pizzaId1.getName());
//        }
//        catch (PersistenceException e) {
//            System.err.println("Erreur de persistance : " + e.getMessage());
//        }
//        finally {
//            if (emf != null) {
//                emf.close();
//            }
//        }


        ///////// TP 02 /////////
        try {
            emf = Persistence.createEntityManagerFactory("pizza-pu");
            EntityManager em = emf.createEntityManager();
            EntityTransaction tx = em.getTransaction();
            tx.begin();

            // récupération du livre avec id = 3
            Livre livre3 = em.find(Livre.class, 3);
            System.out.println("Livre id = 3: " + livre3.getTitre() + " -- " + livre3.getAuteur());

            // insertion d'un nouveau livre
            Livre newLivre = new Livre();
            newLivre.setTitre("Tintin au Congo");
            newLivre.setAuteur("Hergé");
            em.persist(newLivre);
            System.out.println("insertion d'un nouveau livre avec l'id = " + newLivre.getId());

          // Modifiez le titre du livre d’identifiant 5 qui possède une erreur : le nouveau titre doit être
            // « Du plaisir dans la cuisine » au lieu de « 1001 recettes de Cuisine ».
            Livre l5 = em.find(Livre.class, 5);
            l5.setTitre("Du plaisir dans la cuisine");

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
