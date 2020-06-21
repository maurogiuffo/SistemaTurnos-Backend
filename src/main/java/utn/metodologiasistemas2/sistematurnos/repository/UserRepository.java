package utn.metodologiasistemas2.sistematurnos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import utn.metodologiasistemas2.sistematurnos.model.User;
import utn.metodologiasistemas2.sistematurnos.projections.UserTurns;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    List<User> findByFirstName(String firstName);

    @Query("SELECT u FROM User u WHERE u.email = :email AND u.password = :password")
    User findByEmailAndPassword(String email, String password);

     @Query(value = "select u from User u where u.category.categoryName = :categoryName")
     List<UserTurns> findByCategory(String categoryName);
}
