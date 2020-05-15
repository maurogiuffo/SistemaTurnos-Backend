package utn.metodologiasistemas2.sistematurnos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import utn.metodologiasistemas2.sistematurnos.model.Costumer;

import java.util.List;

@Repository
public interface CostumerRepository extends JpaRepository<Costumer,Integer> {

    List<Costumer> findByFirstName(String firstName);

}
