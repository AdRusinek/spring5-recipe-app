package com.rusinek.recipeapp.services;

import com.rusinek.recipeapp.commands.IngredientCommand;
import com.rusinek.recipeapp.converters.IngredientToIngredientCommand;
import com.rusinek.recipeapp.domain.Recipe;
import com.rusinek.recipeapp.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Slf4j
@Service
public class IngredientServiceImpl implements IngredientService{

    private final IngredientToIngredientCommand ingredients;
    private final RecipeRepository recipeRepository;

    public IngredientServiceImpl(IngredientToIngredientCommand ingredients, RecipeRepository recipeRepository) {
        this.ingredients = ingredients;
        this.recipeRepository = recipeRepository;
    }

    @Override
    public IngredientCommand findByRecipeIdAnIngredientdId(Long recipeId, Long ingredientId) {

        Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);

        if(!recipeOptional.isPresent()) {
            //todo impl error handling
            log.debug("recipe id not found " + recipeId);
        }

        Recipe recipe = recipeOptional.get();

        Optional<IngredientCommand> ingredientCommandOptional = recipe.getIngredients().stream()
                .filter(ingredient -> ingredient.getId().equals(ingredientId))
                .map(ingredient -> ingredients.convert(ingredient)).findFirst();

        if(!ingredientCommandOptional.isPresent()) {
            // todo error handling
            log.error("Ingredient id not found: " + ingredientId);
        }
        return ingredientCommandOptional.get();
    }
}
