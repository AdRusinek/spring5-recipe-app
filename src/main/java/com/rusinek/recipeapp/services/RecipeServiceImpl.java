package com.rusinek.recipeapp.services;

import com.rusinek.recipeapp.commands.RecipeCommand;
import com.rusinek.recipeapp.converters.RecipeCommandToRecipe;
import com.rusinek.recipeapp.converters.RecipeToRecipeCommand;
import com.rusinek.recipeapp.domain.Recipe;
import com.rusinek.recipeapp.repositories.RecipeRepository;
import lombok.Synchronized;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;
import java.util.Optional;

@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;
    private final RecipeCommandToRecipe recipeCommandToRecipe;
    private final RecipeToRecipeCommand recipeToRecipeCommand;

    public RecipeServiceImpl(RecipeRepository recipeRepository,
                             RecipeCommandToRecipe recipeCommandToRecipe,
                             RecipeToRecipeCommand recipeToRecipeCommand) {
        this.recipeRepository = recipeRepository;
        this.recipeCommandToRecipe = recipeCommandToRecipe;
        this.recipeToRecipeCommand = recipeToRecipeCommand;
    }

    @Override
    public Set<Recipe> getRecipes() {
        log.debug("Im in the service");
        Set<Recipe> recipes = new HashSet<>();
        recipeRepository.findAll().iterator().forEachRemaining(recipes::add);
        return recipes;
    }

    @Override
    public Recipe findById(Long id) {
        Optional<Recipe> recipe = recipeRepository.findById(id);

        if(!recipe.isPresent()) {
            throw new RuntimeException("Recipe with this id not found");
        }
        return recipe.get();
    }

    @Override
    public void deleteById(Long id) {
        recipeRepository.deleteById(id);
    }

    @Override
    @Transactional
    public RecipeCommand saveRecipeCommand(RecipeCommand recipeCommand) {

        Recipe detachedRecipe = recipeCommandToRecipe.convert(recipeCommand);

        Recipe savedRecipe = recipeRepository.save(detachedRecipe);
        log.debug("Saved Recipe " + savedRecipe.getId());

        return recipeToRecipeCommand.convert(savedRecipe);
    }

    @Override
    @Transactional
    public RecipeCommand findCommandById(Long id) {

      return recipeToRecipeCommand.convert(findById(id));
    }
}
