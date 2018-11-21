package rusinek.springframework.spring5recipeapp.repositories;

import org.springframework.data.repository.CrudRepository;
import rusinek.springframework.spring5recipeapp.domain.Category;

import java.util.Optional;


public interface CategoryRepository extends CrudRepository<Category,Long> {

    // this uses spring data JPA and it will find item for us in this case by description
    Optional<Category> findByDescription(String description);
}
