package flaviodeangelis.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Entity
@Table(name = "utenti")
public class Utente {
    @Id
    long numeroDiTessera;
    private String nome;
    private String cognome;
    private Date dataDiNascita;
    @OneToMany(mappedBy = "elemento")
    private List<Prestito> prestiti;

    public Utente() {
    }

    public Utente(String nome, String cognome, Date dataDiNascita) {
        Random rndm = new Random();
        this.numeroDiTessera = rndm.nextInt(0, 99999999);
        this.nome = nome;
        this.cognome = cognome;
        this.dataDiNascita = dataDiNascita;
    }
}