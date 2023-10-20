package flaviodeangelis.entities;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;

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

    public Element getByIsbn(long isbn) {
        return em.find(Element.class, isbn);
    }

    public List<Element> getByYearOfPublication(int yearOfPublication) {
        TypedQuery<Element> getByYearOfPublication = em.createQuery("SELECT e FROM Element e WHERE e.yearOfPublication = :yearOfPublication", Element.class);
        getByYearOfPublication.setParameter("yearOfPublication", yearOfPublication);
        return getByYearOfPublication.getResultList();
    }

    public List<Books> getByAuthor(String author) {
        TypedQuery<Books> getByAuthor = em.createQuery("SELECT b FROM Books b WHERE b.author = :author", Books.class);
        getByAuthor.setParameter("author", author);
        return getByAuthor.getResultList();
    }

    public List<Element> getByTitle(String title) {
        TypedQuery<Element> getByTitle = em.createQuery("SELECT e FROM Element e WHERE e.title LIKE :title", Element.class);
        getByTitle.setParameter("title", "%" + title + "%");
        return getByTitle.getResultList();
    }
}
