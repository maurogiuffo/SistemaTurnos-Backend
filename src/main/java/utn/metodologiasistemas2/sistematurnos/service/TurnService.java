package utn.metodologiasistemas2.sistematurnos.service;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import utn.metodologiasistemas2.sistematurnos.controller.TurnController;
import utn.metodologiasistemas2.sistematurnos.dto.CreateTurnsDTO;
import utn.metodologiasistemas2.sistematurnos.exceptions.TurnNotexistException;
import utn.metodologiasistemas2.sistematurnos.exceptions.UserNotexistException;
import utn.metodologiasistemas2.sistematurnos.exceptions.ValidationException;
import utn.metodologiasistemas2.sistematurnos.model.Turn;
import utn.metodologiasistemas2.sistematurnos.model.User;
import utn.metodologiasistemas2.sistematurnos.repository.TurnRepository;
import utn.metodologiasistemas2.sistematurnos.repository.UserRepository;

import java.util.*;

import static java.util.Objects.isNull;

@Service
public class TurnService {

    private final TurnRepository turnRepository;
    private final UserRepository userRepository;

    @Autowired
    public TurnService(TurnRepository turnRepository, UserRepository userRepository) {
        this.turnRepository = turnRepository;
        this.userRepository=userRepository;
    }

    public Turn addTurnToUser(int id_user, int id_turn) throws UserNotexistException, TurnNotexistException, ValidationException {


            if (isNull(id_turn) || isNull(id_user)) throw  new ValidationException("You cant have null fields");

            Turn turnAux = turnRepository.findById(id_turn).orElseThrow(    ()-> new TurnNotexistException());

            User userAux  = userRepository.findById(id_user).orElseThrow(   ()-> new UserNotexistException());

            turnRepository.addTurnToUser(id_user, id_turn);

            return turnAux;
    }

    public void addTurn(Turn turn)
    {
        turnRepository.save(turn);
    }

    public List<Turn> getAllTurns()
    {
        return turnRepository.findByCustomerIsNull();
    }

    public void CreateTurnsLote(CreateTurnsDTO createTurnsDTO)
    {
        Optional<User> user= userRepository.findById(createTurnsDTO.userId);

        for (int i = 0; i< createTurnsDTO.cantidadDias; i++ )
        {
            for (int h = 0; h < createTurnsDTO.cantidadTurnos; h ++ )
            {
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(createTurnsDTO.fechaDesde); // Configuramos la fecha que se recibe
                calendar.add(Calendar.DAY_OF_YEAR, i);
                //calendar.add(Calendar.HOUR_OF_DAY,createTurnsDTO.horaDesde);
                calendar.add(Calendar.MINUTE, h * createTurnsDTO.duracionTurno );

                Turn turn  = new Turn();
                turn.setTurnDate(calendar.getTime());
                turn.setProfessional(user.get());

                turnRepository.save(turn);
            }
        }
    }

    public void deleteTurn(Turn turn) throws TurnNotexistException {
        try {
                turnRepository.delete(turn);
        } catch ( Exception E) {
            throw new TurnNotexistException();
        }

    }
}
