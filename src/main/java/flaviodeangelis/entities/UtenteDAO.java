package flaviodeangelis.entities;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;

public class UtenteDAO {
    private final EntityManager em;

    public UtenteDAO(EntityManager em) {
        this.em = em;
    }

    public void save(Utente u) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(u);
        transaction.commit();
        System.out.println("L'utente" + u + "salvato nel DB in modo corretto");
    }

    public void delate(long numeroTessera) {
        Utente elementByNumeroTessera = em.find(Utente.class, numeroTessera);
        if (elementByNumeroTessera != null) {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            em.remove(elementByNumeroTessera);
            transaction.commit();
        } else {
            System.err.println("Evento con id" + numeroTessera + "non esistente");
        }
    }

    public Utente getByNumeroTessera(long isbn) {
        return em.find(Utente.class, isbn);
    }

    public List<Utente> getByName(String nome) {
        TypedQuery<Utente> getByName = em.createQuery("SELECT u FROM Utente u WHERE u.nome LIKE :nome", Utente.class);
        getByName.setParameter("nome", "%" + nome + "%");
        return getByName.getResultList();
    }
}
