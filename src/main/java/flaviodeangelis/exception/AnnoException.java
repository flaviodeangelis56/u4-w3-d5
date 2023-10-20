package flaviodeangelis.exception;

public class AnnoException extends Exception {
    public AnnoException() {
        super("Nessun libro è stato publicato in quell'anno");
    }
}
