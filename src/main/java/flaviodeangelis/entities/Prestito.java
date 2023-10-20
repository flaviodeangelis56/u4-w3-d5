package flaviodeangelis.entities;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "prestiti")
public class Prestito {
    @Id
    @GeneratedValue
    long id;
    @ManyToOne
    @JoinColumn(name = "utente_id", nullable = false)
    Utente utente;
    @ManyToOne
    @JoinColumn(name = "elemento_id", nullable = false)
    Element elemento;
    LocalDate dataInizioPrestito;
    LocalDate dataRestituzionePrevista;
    LocalDate dataRestituzione;


    public Prestito() {
    }

    public Prestito(LocalDate dataInizioPrestito, LocalDate dataRestituzione, Utente utente, Element elemento) {

        this.dataInizioPrestito = dataInizioPrestito;
        this.dataRestituzionePrevista = dataInizioPrestito.plusDays(30);
        this.dataRestituzione = dataRestituzione;
        this.utente = utente;
        this.elemento = elemento;
    }
}
