package exceptions;

public class InvalidSpaceAreaException extends IllegalArgumentException {

    public InvalidSpaceAreaException() {

    }

    public InvalidSpaceAreaException(String exceptionMessage) {
        super(exceptionMessage);
    }
}
