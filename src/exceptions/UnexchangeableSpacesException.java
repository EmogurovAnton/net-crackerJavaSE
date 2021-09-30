package exceptions;

public class UnexchangeableSpacesException extends IllegalArgumentException {
    public UnexchangeableSpacesException() {

    }

    public UnexchangeableSpacesException(String exceptionMessage) {
        super(exceptionMessage);
    }

    public void showMessage() {
        String exceptionMessage = "Spaces exchange is impossible";
        System.err.println(exceptionMessage);
    }
}
