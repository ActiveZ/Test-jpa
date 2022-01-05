package utils;

import entities.CustomerOrder;
import entities.Drink;
import entities.Pizza;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class Utils {

    public static void listPizzas(EntityManager em){
        TypedQuery<Pizza> query = em.createQuery("select p from Pizza p", Pizza.class);
        List<Pizza> listPizzas = query.getResultList();
        System.out.println("----- Liste des pizzas -----");
        for (Pizza p : listPizzas) {
            System.out.println(p.toString());
        }
    }

    public static void listDrinks(EntityManager em){
        TypedQuery<Drink> query = em.createQuery("select d from Drink d", Drink.class);
        List<Drink> listDrinks = query.getResultList();
        System.out.println("----- Liste des boissons -----");
        for (Drink d : listDrinks) {
            System.out.println(d.toString());
        }
    }

    public static void listOrders(EntityManager em){
        TypedQuery<CustomerOrder> query = em.createQuery("select o from CustomerOrder o", CustomerOrder.class);
        List<CustomerOrder> listOrders = query.getResultList();
        System.out.println("----- Liste des commandes -----");
        for (CustomerOrder o : listOrders) {
            System.out.println(o.toString());
        }
    }
}
