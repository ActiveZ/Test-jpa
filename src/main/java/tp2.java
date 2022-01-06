import entities.tp.Livre;

import javax.persistence.*;
import java.util.List;

public class tp2 {
    public static void main(String[] args) {
        EntityManagerFactory emf = null;

        try {
            emf = Persistence.createEntityManagerFactory("biblio-pu");
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


//           Faites une requête JPQL pour extraire de la base un livre en fonction de son titre.
            Query query = em.createQuery("SELECT l FROM Livre l WHERE l.titre='Germinal'");
            List<Livre> result1 = query.getResultList(); // si on est sur d'avoir un et un seul résultat, sinon erreur noReultException
            //System.out.println(query.getSingleResult().getAuteur()); // a revoir
            System.out.println("lQuery.get(0) Titre: " + result1.get(0).getTitre() + " -- " + result1.get(0).getAuteur());

//           Faites une requête JPQL pour extraire de la base un livre en fonction de son auteur.
            query = em.createQuery("SELECT l FROM Livre l WHERE l.auteur=:auteurLivre");
            query.setParameter("auteurLivre", "Jules Verne");
            List<Livre> result2 = query.getResultList();
            System.out.println("lQuery.get(0) Titre: " + result2.get(0).getTitre() + " -- " + result2.get(0).getAuteur());

//           Supprimez un livre de votre choix en base de données.
            Livre lDead = em.find(Livre.class, 4);
            em.remove(lDead);

//           Affichez la liste de tous les livres présents en base de données (titre et auteur).
            System.out.println("///////// liste des livres ///////////");
            TypedQuery<Livre> queryAll = em.createQuery("select l from Livre l", Livre.class);
            List<Livre> listeLivres = queryAll.getResultList();
            for (Livre l : listeLivres) {
                System.out.println(l.getTitre() + " -- " + l.getAuteur());
            }

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

