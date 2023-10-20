package flaviodeangelis.exception;

public class InputMeseException extends Exception {
    public InputMeseException() {
        super("Mese non valido un elemento non può essere publicato in un anno non esistente");
    }
}