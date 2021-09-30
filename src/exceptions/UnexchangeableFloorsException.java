package exceptions;

public class UnexchangeableFloorsException extends IllegalArgumentException {

    public UnexchangeableFloorsException() {

    }

    public UnexchangeableFloorsException(String exceptionMessage) {
        super(exceptionMessage);
    }

    public void showMessage() {
        String exceptionMessage = "Floors exchange is impossible";
        System.err.println(exceptionMessage);
    }

}
