package utn.metodologiasistemas2.sistematurnos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import utn.metodologiasistemas2.sistematurnos.model.Turn;

import java.util.List;

@Repository
public interface TurnRepository extends JpaRepository<Turn,Integer> {


}
