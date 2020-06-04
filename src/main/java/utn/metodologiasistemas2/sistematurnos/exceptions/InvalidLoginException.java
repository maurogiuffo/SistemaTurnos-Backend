package utn.metodologiasistemas2.sistematurnos.exceptions;

public class InvalidLoginException extends Throwable {
    public InvalidLoginException(Throwable cause) {
        super(cause);
    }

    public String getMessage() {
        return "Invalid login";
    }
}
