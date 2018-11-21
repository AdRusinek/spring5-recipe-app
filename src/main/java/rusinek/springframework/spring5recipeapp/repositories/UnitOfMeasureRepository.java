package rusinek.springframework.spring5recipeapp.repositories;

import org.springframework.data.repository.CrudRepository;
import rusinek.springframework.spring5recipeapp.domain.UnitOfMeasure;

// those 3 repositories are created as a spring beans in the context and if we had anything that needed them
// It would have got injected in here
public interface UnitOfMeasureRepository extends CrudRepository<UnitOfMeasure,Long> {
}
