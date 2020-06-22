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
import utn.metodologiasistemas2.sistematurnos.model.Turn;
import utn.metodologiasistemas2.sistematurnos.model.User;
import utn.metodologiasistemas2.sistematurnos.repository.TurnRepository;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import static java.util.Objects.isNull;

@Service
public class TurnService {

    private final TurnRepository turnRepository;

    @Autowired
    public TurnService(TurnRepository turnRepository) {
        this.turnRepository = turnRepository;
    }

    public ResponseEntity addTurnToUser(int id_user, int id_turn) throws UserNotexistException, TurnNotexistException {

        try{

            turnRepository.addTurnToUser(id_user, id_turn);

            ResponseEntity.ok(HttpStatus.CREATED);
        }catch (Exception e)
        {
            if(isNull(id_user))
            {
                System.out.println("No se ha encontrado el usuario");
                return new ResponseEntity("Error con el Turno",HttpStatus.FORBIDDEN);
            }
            else if(isNull(id_turn))
            {
                System.out.println("No se ha encontrado el turno");
                return new ResponseEntity("Error con el Turno",HttpStatus.FORBIDDEN);
            }else {
                System.out.println("Hubo un problema con el sistema");
            }
        }
        return new ResponseEntity("Error SQL",HttpStatus.CONFLICT);
    }

    public void addTurn(Turn turn)
    {
        turnRepository.save(turn);
    }

    public List<Turn> getAllTurns()
    {
        return turnRepository.findAll();
    }

    public void CreateTurnsLote(CreateTurnsDTO createTurnsDTO, User user)
    {
        for (int i = 0; i< createTurnsDTO.cantidadDias; i++ )
        {
            for (int h = 0; h < createTurnsDTO.cantidadTurnos; h ++ )
            {
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(createTurnsDTO.fechaDesde); // Configuramos la fecha que se recibe
                calendar.add(Calendar.DAY_OF_YEAR, i);
                calendar.add(Calendar.HOUR_OF_DAY,createTurnsDTO.horaDesde);
                calendar.add(Calendar.MINUTE, h * createTurnsDTO.duracionTurno );

                Turn turn  = new Turn();
                turn.setTurnDate(calendar.getTime());
                turn.setProfessional(user);

                turnRepository.save(turn);
            }
        }
    }
}
