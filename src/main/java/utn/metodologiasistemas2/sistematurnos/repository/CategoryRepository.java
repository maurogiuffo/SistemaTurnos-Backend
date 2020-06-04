package utn.metodologiasistemas2.sistematurnos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import utn.metodologiasistemas2.sistematurnos.model.Category;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

    List<Category> findByCategoryName (String categoryName);
}
