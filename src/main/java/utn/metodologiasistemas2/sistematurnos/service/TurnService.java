package utn.metodologiasistemas2.sistematurnos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utn.metodologiasistemas2.sistematurnos.model.Turn;
import utn.metodologiasistemas2.sistematurnos.repository.TurnRepository;

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
}
