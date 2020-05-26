package utn.metodologiasistemas2.sistematurnos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import utn.metodologiasistemas2.sistematurnos.model.Costumer;
import utn.metodologiasistemas2.sistematurnos.service.CostumerService;


import java.util.List;

@RestController
@RequestMapping("/costumer")
public class CostumerController {

    private final CostumerService costumerService;

    @Autowired
    public CostumerController(CostumerService costumerService) {
        this.costumerService = costumerService;
    }


    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/{id_costumer}")
    public Costumer getCostumerById(@PathVariable Integer id_costumer) {
        return costumerService.getCostumerById(id_costumer);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/")
    public void AddCostumer(@RequestBody Costumer costumer) {
        costumerService.addCostumer(costumer);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/")
    public List<Costumer> getAll(@RequestParam(required = false) String firstname){
        return costumerService.getAllCostumers(firstname);
    }
}
