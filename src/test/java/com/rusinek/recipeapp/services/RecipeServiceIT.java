package com.rusinek.recipeapp.services;

import com.rusinek.recipeapp.commands.RecipeCommand;
import com.rusinek.recipeapp.converters.RecipeCommandToRecipe;
import com.rusinek.recipeapp.converters.RecipeToRecipeCommand;
import com.rusinek.recipeapp.domain.Recipe;
import com.rusinek.recipeapp.repositories.RecipeRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RecipeServiceIT {

    public static final String NEW_DESCRIPTION = "New Description";

    @Autowired
    RecipeService recipeService;

    @Autowired
    RecipeRepository recipeRepository;

    @Autowired
    RecipeCommandToRecipe recipeCommandToRecipe;

    @Autowired
    RecipeToRecipeCommand recipeToRecipeCommand;


    @Transactional
    @Test
    public void testSaveOfDescription() throws Exception {

        Iterable<Recipe> recipes = recipeRepository.findAll();
        Recipe testRecipe = recipes.iterator().next();
        RecipeCommand testRecipeCommand = recipeToRecipeCommand.convert(testRecipe);

        // when
        testRecipeCommand.setDescription(NEW_DESCRIPTION);
        RecipeCommand savedRecipeCommand = recipeService.saveRecipeCommand(testRecipeCommand);


        //then
        assertEquals(NEW_DESCRIPTION,savedRecipeCommand.getDescription());
        assertEquals(testRecipe.getId(),savedRecipeCommand.getId());
        assertEquals(testRecipe.getCategories().size(),testRecipeCommand.getCategories().size());
        assertEquals(testRecipe.getIngredients().size(),testRecipeCommand.getIngredients().size());

    }

}