package flaviodeangelis.exception;

public class ChooseInputException extends Exception {
    public ChooseInputException() {
        super("Azione non esistente scegli un azione tra quelle esistenti");
    }
}
