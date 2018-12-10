package rusinek.springframework.spring5recipeapp.converters;

import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import rusinek.springframework.spring5recipeapp.commands.RecipeCommand;
import rusinek.springframework.spring5recipeapp.domain.Recipe;

@Component
public class RecipeCommandToRecipe implements Converter<RecipeCommand, Recipe> {

    private final CategoryCommandToCategory categoryConverter;
    private final IngredientCommandToIngredient ingredientConverter;
    private final NotesCommandToNotes notesConverter;

    public RecipeCommandToRecipe(CategoryCommandToCategory categoryCommandToCategory,
    IngredientCommandToIngredient ingredientCommandToIngredient, NotesCommandToNotes notesCommandToNotes) {
        this.categoryConverter = categoryCommandToCategory;
        this.ingredientConverter = ingredientCommandToIngredient;
        this.notesConverter = notesCommandToNotes;
    }

    @Nullable
    @Synchronized
    @Override
    public Recipe convert(RecipeCommand source) {
        if(source == null) {
            return null;
        }

        Recipe recipe = new Recipe();

        recipe.setSource(source.getSource());
        recipe.setServings(source.getServings());
        recipe.setUrl(source.getUrl());
        recipe.setId(source.getId());
        recipe.setCookTime(source.getCookTime());
        recipe.setDescription(source.getDescription());
        recipe.setPrepTime(source.getPrepTime());
        recipe.setDifficulty(source.getDifficulty());
        recipe.setDirections(source.getDirections());
        recipe.setNotes(notesConverter.convert(source.getNotes()));


        if(source.getCategories() != null && source.getCategories().size() > 0) {
            source.getCategories()
                    .forEach(category -> recipe.getCategories().add(categoryConverter.convert(category)));
        }

        if(source.getIngredients() != null && source.getIngredients().size() > 0) {
            source.getIngredients()
                    .forEach(ingredient -> recipe.getIngredients().add(ingredientConverter.convert(ingredient)));
        }

        return recipe;
    }
}
