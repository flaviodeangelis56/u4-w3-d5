package flaviodeangelis.exception;

public class InputAnnoException extends Exception {
    public InputAnnoException() {
        super("Anno non valido un elemento non può essere publicato in un anno non esistente");
    }
}
