package utn.metodologiasistemas2.sistematurnos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import utn.metodologiasistemas2.sistematurnos.model.Turn;
import utn.metodologiasistemas2.sistematurnos.service.TurnService;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/turn")
public class TurnController {

    private final TurnService turnService;

    @Autowired
    public TurnController(TurnService turnService) {
        this.turnService = turnService;
    }

    @PostMapping("/")
    public void addTurn(@RequestBody Turn turn)
    {
        turnService.addTurn(turn);
    }

    @GetMapping("/")
    public List<Turn> getAll() {
        return turnService.getAllTurns();
    }
}
