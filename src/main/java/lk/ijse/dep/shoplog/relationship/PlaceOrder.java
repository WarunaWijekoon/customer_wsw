package lk.ijse.dep.shoplog.relationship;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import lk.ijse.dep.shoplog.relationship.entity.*;
import lk.ijse.dep.shoplog.relationship.util.JpaUtil;


import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public class PlaceOrder {
    public static void main(String[] args) {
        try (EntityManagerFactory emf = JpaUtil.getEntityManagerFactory();
             EntityManager em = emf.createEntityManager()) {
            EntityTransaction tx = em.getTransaction();

            try {
                tx.begin();

                Item item1 = em.find(Item.class, "I0001");
                Item item2 = em.find(Item.class, "I0002");
                Item item3 = em.find(Item.class, "I0003");
                User user1 = em.find(User.class, "wsw");
                User user2 = em.find(User.class, "asd");
                Customer customer2 = em.find(Customer.class, "C002");
                Customer customer1 = em.find(Customer.class, "C001");
                Order order1 = new Order("OD001", Date.valueOf(LocalDate.now()), user2, customer2);
                Order order2 = new Order("OD002", Date.valueOf(LocalDate.now()), user1, customer1);

                OrderDetail orderDetail1 = new OrderDetail(order1, item1, item1.getPrice(), 10);
                OrderDetail orderDetail2 = new OrderDetail(order1, item2, item2.getPrice(), 20);
                OrderDetail orderDetail3 = new OrderDetail(order2, item3, item3.getPrice(), 30);



                List.of(orderDetail1, orderDetail2, orderDetail3).forEach(em::persist);

                tx.commit();
            } catch (Throwable t) {
                tx.rollback();
                t.printStackTrace();
            }
        }
    }
}
