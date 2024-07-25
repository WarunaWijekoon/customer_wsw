package lk.ijse.dep.shoplog.relationship;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import lk.ijse.dep.shoplog.relationship.entity.Item;
import lk.ijse.dep.shoplog.relationship.util.JpaUtil;


import java.math.BigDecimal;
import java.util.List;

public class SaveItem {
    public static void main(String[] args) {
        try (EntityManagerFactory emf = JpaUtil.getEntityManagerFactory();
             EntityManager em = emf.createEntityManager()) {
            EntityTransaction tx = em.getTransaction();

            try {
                tx.begin();

                Item item1 = new Item("I0001", "Mouse", 100, new BigDecimal("300"));
                Item item2 = new Item("I0002", "Keyboard", 20, new BigDecimal("3300"));
                Item item3 = new Item("I0003", "Monitor", 220, new BigDecimal("5000"));
                Item item4 = new Item("I0004", "Head Phone", 330, new BigDecimal("7000"));

                List.of(item1, item2, item3, item3, item4).forEach(em::persist);

                tx.commit();
            } catch (Throwable t) {
                tx.rollback();
                t.printStackTrace();
            }
        }
    }
}
