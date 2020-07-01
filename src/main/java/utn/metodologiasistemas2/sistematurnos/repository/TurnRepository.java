package utn.metodologiasistemas2.sistematurnos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import utn.metodologiasistemas2.sistematurnos.model.Turn;
import utn.metodologiasistemas2.sistematurnos.projections.TurnProjection;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface TurnRepository extends JpaRepository<Turn,Integer> {


    @Modifying
    @Query(value = "UPDATE turns\n" +
            "set customer_id_user = ?1\n" +
            "where turns.id_turn = ?2",nativeQuery = true)
    @Transactional
    int addTurnToUser(int id_user, int id_turn);

   // @Query("SELECT f FROM Turn f where f.User = null")
    List<Turn> findByCustomerIsNull();


}
