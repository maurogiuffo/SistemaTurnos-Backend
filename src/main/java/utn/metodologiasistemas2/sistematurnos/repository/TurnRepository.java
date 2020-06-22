package utn.metodologiasistemas2.sistematurnos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import utn.metodologiasistemas2.sistematurnos.model.Turn;

import java.util.Optional;

@Repository
public interface TurnRepository extends JpaRepository<Turn,Integer> {


    @Query(value = "UPDATE turns\n" +
            "set customer_id_user = ?1\n" +
            "where turns.id_turn = ?2",nativeQuery = true)
    Turn addTurnToUser(int id_user, int id_turn);

}
