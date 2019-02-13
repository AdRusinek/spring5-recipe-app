package com.rusinek.recipeapp.repositories;

import com.rusinek.recipeapp.domain.Recipe;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


public interface RecipeRepository extends CrudRepository<Recipe,Long> {


}
