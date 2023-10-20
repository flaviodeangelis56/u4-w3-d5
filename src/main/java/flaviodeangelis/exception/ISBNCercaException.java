package flaviodeangelis.exception;

public class ISBNCercaException extends Exception {
    public ISBNCercaException() {
        super("ISBN non trovato non ci sono libri con questo codice");
    }
}
