package lk.ijse.dep.shoplog.relationship;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import lk.ijse.dep.shoplog.relationship.entity.Contact;
import lk.ijse.dep.shoplog.relationship.entity.Customer;
import lk.ijse.dep.shoplog.relationship.util.JpaUtil;


import java.util.List;

public class SaveCustomer {
    public static void main(String[] args) {
        try (EntityManagerFactory emf = JpaUtil.getEntityManagerFactory();
             EntityManager em = emf.createEntityManager()) {
            EntityTransaction tx = em.getTransaction();

            try {
                tx.begin();
                Customer customer1 = new Customer("C001", "nadun kumara", "kandy");
                Customer customer2 = new Customer("C002", "sanjeewa lakshan", "gampaha");


                Contact contact1 = new Contact(customer1, "123456789");
                Contact contact2 = new Contact(customer2, "234567891");
                Contact contact3 = new Contact(customer2, "436567657");


                List.of(contact1, contact1, contact2, contact3).forEach(em::persist);


                tx.commit();
            } catch (Throwable t) {
                tx.rollback();
                t.printStackTrace();
            }
        }
    }
}
