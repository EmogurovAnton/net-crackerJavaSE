package exceptions;

public class SpaceIndexOutOfBoundsException extends IndexOutOfBoundsException {

    public SpaceIndexOutOfBoundsException() {
    }

    public SpaceIndexOutOfBoundsException(String message) {
        super(message);
    }


    public void showMessage() {
        String exceptionMessage = "Space index is invalid";
        System.err.println(exceptionMessage);
    }
}
