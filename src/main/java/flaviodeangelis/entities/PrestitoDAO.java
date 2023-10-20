package flaviodeangelis.entities;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;

public class PrestitoDAO {
    private final EntityManager em;

    public PrestitoDAO(EntityManager em) {
        this.em = em;
    }

    public void save(Prestito p) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(p);
        transaction.commit();
        System.out.println("Il prestito" + p + "salvato nel DB in modo corretto");
    }

    public void delate(long id) {
        Prestito prestitoById = em.find(Prestito.class, id);
        if (prestitoById != null) {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            em.remove(prestitoById);
            transaction.commit();
        } else {
            System.err.println("Prestito con id" + id + "non esistente");
        }
    }

    public List<Prestito> getPrestitoByNumeroTesseraUtente(long numeroTessera) {
        TypedQuery<Prestito> getPrestitoByNumeroTesseraUtente = em.createQuery(
                "SELECT p FROM Prestito p " +
                        "JOIN p.utente u " +
                        "WHERE u.numeroDiTessera = :numeroTessera " +
                        "AND p.dataRestituzione IS NULL", Prestito.class);
        getPrestitoByNumeroTesseraUtente.setParameter("numeroTessera", numeroTessera);

        return getPrestitoByNumeroTesseraUtente.getResultList();
    }

    public List<Prestito> getPrestitoScaduto() {
        TypedQuery<Prestito> getPrestitoScaduto = em.createQuery("SELECT p FROM Prestito p WHERE p.dataRestituzionePrevista < CURRENT_DATE AND p.dataRestituzione IS NULL", Prestito.class);
        return getPrestitoScaduto.getResultList();
    }
}
