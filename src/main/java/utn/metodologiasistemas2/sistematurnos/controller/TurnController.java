package utn.metodologiasistemas2.sistematurnos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utn.metodologiasistemas2.sistematurnos.dto.CreateTurnsDTO;
import utn.metodologiasistemas2.sistematurnos.exceptions.TurnNotexistException;
import utn.metodologiasistemas2.sistematurnos.exceptions.UserNotexistException;
import utn.metodologiasistemas2.sistematurnos.model.Turn;
import utn.metodologiasistemas2.sistematurnos.model.User;
import utn.metodologiasistemas2.sistematurnos.service.TurnService;
import utn.metodologiasistemas2.sistematurnos.session.SessionManager;

import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/turn")
public class TurnController {

    private final TurnService turnService;
    private final SessionManager sessionManager;

    @Autowired
    public TurnController(TurnService turnService, SessionManager sessionManager) {

        this.turnService = turnService;
        this.sessionManager= sessionManager;
    }

    @PostMapping("/addCustomerTurn")
    public ResponseEntity addTurnToUser(@RequestParam int id_user , int id_turn)
    {
        ResponseEntity response;

        try {
            response = ResponseEntity.ok(turnService.addTurnToUser(id_user,id_turn));

        } catch (UserNotexistException e) {

            response = new ResponseEntity(e.getMessage(), HttpStatus.CONFLICT);
        } catch (TurnNotexistException e) {
            e.printStackTrace();
            response = new ResponseEntity(e.getMessage(), HttpStatus.CONFLICT);
        }
        return response;
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

    @PostMapping("/createLote")
    public void CreateTurnsLote(@RequestBody CreateTurnsDTO createTurnDTO)
    {
        //User user= sessionManager.getCurrentUser(token);

        turnService.CreateTurnsLote(createTurnDTO);
    }
}
