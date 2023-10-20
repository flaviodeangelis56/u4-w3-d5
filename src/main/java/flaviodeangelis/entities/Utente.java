package flaviodeangelis.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;

@Entity
@Table(name = "utenti")
public class Utente {
    @Id
    long numeroDiTessera;
    private String nome;
    private String cognome;
    private LocalDate dataDiNascita;
    @OneToMany(mappedBy = "elemento")
    private List<Prestito> prestiti;

    public Utente() {
    }

    public Utente(String nome, String cognome, LocalDate dataDiNascita) {
        Random rndm = new Random();
        this.numeroDiTessera = rndm.nextInt(0, 99999999);
        this.nome = nome;
        this.cognome = cognome;
        this.dataDiNascita = dataDiNascita;
    }

    @Override
    public String toString() {
        return "Utente{" +
                "numeroDiTessera=" + numeroDiTessera +
                ", nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", dataDiNascita=" + dataDiNascita +
                '}';
    }
}
