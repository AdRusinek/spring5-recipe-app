package com.rusinek.recipeapp.converters;

import com.rusinek.recipeapp.commands.RecipeCommand;
import com.rusinek.recipeapp.domain.Category;
import com.rusinek.recipeapp.domain.Recipe;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class RecipeToRecipeCommand implements Converter<Recipe, RecipeCommand> {

    private final NotesToNotesCommand notes;
    private final IngredientToIngredientCommand ingredients;
    private final CategoryToCategoryCommand categories;

    public RecipeToRecipeCommand(NotesToNotesCommand notes,
                                 IngredientToIngredientCommand ingredients,
                                 CategoryToCategoryCommand categories) {
        this.notes = notes;
        this.ingredients = ingredients;
        this.categories = categories;
    }

    @Nullable
    @Synchronized
    @Override
    public RecipeCommand convert(Recipe source) {
        if(source == null) {
            return null;
        }

        RecipeCommand recipeCommand = new RecipeCommand();

        recipeCommand.setId(source.getId());
        recipeCommand.setCookTime(source.getCookTime());
        recipeCommand.setDescription(source.getDescription());
        recipeCommand.setDifficulty(source.getDifficulty());
        recipeCommand.setDirections(source.getDirections());
        recipeCommand.setPrepTime(source.getPrepTime());
        recipeCommand.setServings(source.getServings());
        recipeCommand.setSource(source.getSource());
        recipeCommand.setUrl(source.getUrl());
        recipeCommand.setNotes(notes.convert(source.getNotes()));

        if(source.getCategories() != null && source.getCategories().size() > 0) {
            source.getCategories().forEach((Category category) -> recipeCommand.getCategories()
                    .add(categories.convert(category)));
        }

        if(source.getIngredients() != null && source.getIngredients().size() > 0) {
            source.getIngredients().forEach(ingredient -> recipeCommand.getIngredients()
                    .add(ingredients.convert(ingredient)));
        }
            return recipeCommand;
    }
}
