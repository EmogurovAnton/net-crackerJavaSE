package exceptions;

public class InvalidRoomsCountException extends IllegalArgumentException {

    public InvalidRoomsCountException() {

    }

    public InvalidRoomsCountException(String exceptionMessage) {
        super(exceptionMessage);
    }
}
