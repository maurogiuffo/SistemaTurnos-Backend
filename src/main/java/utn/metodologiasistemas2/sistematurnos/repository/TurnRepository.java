package utn.metodologiasistemas2.sistematurnos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import utn.metodologiasistemas2.sistematurnos.model.Turn;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface TurnRepository extends JpaRepository<Turn,Integer> {


    @Modifying
    @Query(value = "UPDATE turns\n" +
            "set customer_id_user = ?1\n" +
            "where turns.id_turn = ?2",nativeQuery = true)
    @Transactional
    int addTurnToUser(int id_user, int id_turn);

}
