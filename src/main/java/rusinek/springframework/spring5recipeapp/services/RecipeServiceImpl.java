package rusinek.springframework.spring5recipeapp.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rusinek.springframework.spring5recipeapp.commands.RecipeCommand;
import rusinek.springframework.spring5recipeapp.converters.RecipeCommandToRecipe;
import rusinek.springframework.spring5recipeapp.converters.RecipeToRecipeCommand;
import rusinek.springframework.spring5recipeapp.domain.Recipe;
import rusinek.springframework.spring5recipeapp.repositories.RecipeRepository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j // logger
@Service
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;
    private final RecipeCommandToRecipe recipeCommandToRecipe;
    private final RecipeToRecipeCommand recipeToRecipeCommand;

    public RecipeServiceImpl(RecipeRepository recipeRepository,
         RecipeCommandToRecipe recipeCommandToRecipe, RecipeToRecipeCommand recipeToRecipeCommand) {
        this.recipeRepository = recipeRepository;
        this.recipeCommandToRecipe = recipeCommandToRecipe;
        this.recipeToRecipeCommand = recipeToRecipeCommand;
    }

    @Override
    public Set<Recipe> getRecipes() {
        Set<Recipe> recipes = new HashSet<>();
        recipeRepository.findAll().iterator().forEachRemaining(recipes::add);
        return recipes;
    }

    @Override
    public Recipe findById(Long l) {
        Optional<Recipe> recipe = recipeRepository.findById(l);
        if(!recipe.isPresent()) {
            throw new RuntimeException("Recipe not found");
        }
        return recipe.get();
    }

    @Override
    @Transactional
    public RecipeCommand saveRecipeCommand(RecipeCommand command) {
        Recipe detachedRecipe = recipeCommandToRecipe.convert(command);

        Recipe savedRecipe = recipeRepository.save(detachedRecipe);
        log.debug("Saved Recipe Id: " + savedRecipe.getId());

        return recipeToRecipeCommand.convert(savedRecipe);
    }
}
