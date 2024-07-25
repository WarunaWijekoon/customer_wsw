package lk.ijse.dep.shoplog.relationship;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import lk.ijse.dep.shoplog.relationship.entity.User;
import lk.ijse.dep.shoplog.relationship.util.JpaUtil;


import java.util.List;

public class SaveUser {
    public static void main(String[] args) {
        try (EntityManagerFactory emf = JpaUtil.getEntityManagerFactory();
             EntityManager em = emf.createEntityManager()) {
            EntityTransaction tx = em.getTransaction();

            try {
                tx.begin();

                User user1 = new User("wsw", "nadun kumara", "zxc");
                User user2 = new User("asd", "sanjeewa lakshan", "asd");

                List.of(user1, user2).forEach(em::persist);

                tx.commit();
            } catch (Throwable t) {
                tx.rollback();
                t.printStackTrace();
            }
        }
    }
}
