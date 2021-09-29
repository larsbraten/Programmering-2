package model;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class AddressBookDBHandler implements AddressBook {
    private final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("contacts-pu-ext-db");

    @Override
    public void addContact(ContactDetails contact) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(contact);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public void removeContact(String phoneNumber) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.remove(entityManager.find(ContactDetails.class, phoneNumber));
        entityManager.getTransaction().commit();
        entityManager.close();


    }

    @Override
    public Collection<ContactDetails> getAllContacts() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List contactDetailsList = null;

        String jpql = "SELECT c FROM ContactDetails c";
        Query query = entityManager.createQuery(jpql);

        contactDetailsList = query.getResultList();

        entityManager.close();
        return contactDetailsList;
    }

    @Override
    public Iterator<ContactDetails> iterator() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<ContactDetails> contactDetailsList = null;

        String jqpl = "SELECT c FROM ContactDetails c";
        Query query = entityManager.createQuery(jqpl);

        contactDetailsList = query.getResultList();

        entityManager.close();
        return contactDetailsList.iterator();
    }

    @Override
    public void close() {
        entityManagerFactory.close();
    }
}
