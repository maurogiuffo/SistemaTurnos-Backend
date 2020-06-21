package utn.metodologiasistemas2.sistematurnos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utn.metodologiasistemas2.sistematurnos.controller.TurnController;
import utn.metodologiasistemas2.sistematurnos.dto.CreateTurnsDTO;
import utn.metodologiasistemas2.sistematurnos.model.Turn;
import utn.metodologiasistemas2.sistematurnos.model.User;
import utn.metodologiasistemas2.sistematurnos.repository.TurnRepository;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class TurnService {

    private final TurnRepository turnRepository;

    @Autowired
    public TurnService(TurnRepository turnRepository) {
        this.turnRepository = turnRepository;
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
