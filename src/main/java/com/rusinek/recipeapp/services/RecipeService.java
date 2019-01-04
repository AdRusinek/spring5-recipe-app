package com.rusinek.recipeapp.services;

import com.rusinek.recipeapp.commands.RecipeCommand;
import com.rusinek.recipeapp.domain.Recipe;

import java.util.Set;


public interface RecipeService {

    Set<Recipe> getRecipes();

    Recipe findById(Long id);

    void deleteById(Long id);

    RecipeCommand saveRecipeCommand(RecipeCommand recipeCommand);

    RecipeCommand findCommandById(Long id);

}
