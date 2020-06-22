package utn.metodologiasistemas2.sistematurnos.exceptions;

public class TurnNotexistException extends Throwable{

    public String getMessage()
    {
        return "Turn not exists !";
    }
}
