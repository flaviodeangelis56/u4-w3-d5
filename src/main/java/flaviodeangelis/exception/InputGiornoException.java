package flaviodeangelis.exception;

public class InputGiornoException extends Exception {
    public InputGiornoException() {
        super("Giorno non valido un elemento non può essere publicato in un anno non esistente");
    }
}
