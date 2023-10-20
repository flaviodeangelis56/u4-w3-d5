package flaviodeangelis.exception;

public class TitoloException extends Exception {
    public TitoloException() {
        super("Non ci sono libri con questo titolo");
    }
}
