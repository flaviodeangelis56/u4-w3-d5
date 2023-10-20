package flaviodeangelis.exception;

public class ElementException extends Exception {
    public ElementException() {
        super("elemento non disponibile puoi aggiungere solo book o magazine");
    }
}
