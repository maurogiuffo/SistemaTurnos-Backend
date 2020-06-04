package utn.metodologiasistemas2.sistematurnos.exceptions;

public class UserNotexistException extends Throwable {

    public String getMessage() {
        return "User not exist !";
    }

}
