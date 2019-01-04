package com.rusinek.recipeapp.services;

import com.rusinek.recipeapp.commands.IngredientCommand;

public interface IngredientService {

    IngredientCommand findByRecipeIdAnIngredientdId(Long recipeId, Long ingredientId);
}
