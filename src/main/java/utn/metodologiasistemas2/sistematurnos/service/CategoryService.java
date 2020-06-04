package utn.metodologiasistemas2.sistematurnos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utn.metodologiasistemas2.sistematurnos.model.Category;
import utn.metodologiasistemas2.sistematurnos.repository.CategoryRepository;

import java.util.List;

import static java.util.Objects.isNull;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public void addCategory(Category category)
    {
        categoryRepository.save(category);
    }

    public Category getCategoryById(Integer i)
    {
        return categoryRepository.findById(i).get();
    }

    public List<Category> getAllCategories(String categoryName)
    {
        if(isNull(categoryName)) {
            return  categoryRepository.findAll();
        }
        return  categoryRepository.findByCategoryName(categoryName);
    }

}
