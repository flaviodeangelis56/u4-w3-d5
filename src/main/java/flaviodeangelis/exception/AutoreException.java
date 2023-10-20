package flaviodeangelis.exception;

public class AutoreException extends Exception {
    public AutoreException() {
        super("Non ci sono libri scritti da questo autore");
    }
}
