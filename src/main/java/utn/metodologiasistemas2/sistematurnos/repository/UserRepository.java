package utn.metodologiasistemas2.sistematurnos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import utn.metodologiasistemas2.sistematurnos.model.User;
import utn.metodologiasistemas2.sistematurnos.projections.TurnProjection;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    List<User> findByFirstName(String firstName);

    @Query("SELECT u FROM User u WHERE u.email = :email AND u.password = :password")
    User findByEmailAndPassword(String email, String password);

     //@Query(value = "select u from User u where u.category.categoryName = :categoryName")
     @Query(value = "select u from User u where u.category.categoryName = :categoryName")
     List<User> findByCategory(String categoryName);

    @Query(value = "Select t.id_turn as Id, t.customer_id_user as CustomerId, t.professional_id_user as ProfessionalId,\n" +
            "t.turn_date as TurnDate,t.created_at as CreatedAt,DATE_FORMAT(t.turn_date,'%T') as TurnTime\n" +
            "FROM Turns as T where t.professional_id_user = ?1 ",nativeQuery = true)
    List<TurnProjection> getTurnProjectionById(int professionalId);
}
