package flaviodeangelis.entities;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class ElementDAO {
    private final EntityManager em;

    public ElementDAO(EntityManager em) {
        this.em = em;
    }

    public void save(Element e) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(e);
        transaction.commit();
        System.out.println("L'evento" + e + "salvato nel DB in modo corretto");
    }

    public void delate(long isbn) {
        Element elementByIsbn = em.find(Element.class, isbn);
        if (elementByIsbn != null) {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            em.remove(elementByIsbn);
            transaction.commit();
        } else {
            System.err.println("Evento con id" + isbn + "non esistente");
        }
    }
}
