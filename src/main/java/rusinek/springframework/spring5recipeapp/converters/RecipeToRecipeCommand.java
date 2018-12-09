package rusinek.springframework.spring5recipeapp.converters;

import jdk.internal.jline.internal.Nullable;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import rusinek.springframework.spring5recipeapp.commands.RecipeCommand;
import rusinek.springframework.spring5recipeapp.domain.Category;
import rusinek.springframework.spring5recipeapp.domain.Ingredient;
import rusinek.springframework.spring5recipeapp.domain.Recipe;

@Component
public class RecipeToRecipeCommand implements Converter<Recipe, RecipeCommand> {

    private final CategoryToCategoryCommand categoryConverter;
    private final NotesToNotesCommand notesConverter;
    private final IngredientToIngredientCommand ingredientConverter;

    public RecipeToRecipeCommand(CategoryToCategoryCommand categoryConverter, NotesToNotesCommand notesConverter,
                 IngredientToIngredientCommand ingredientConverter) {
        this.categoryConverter = categoryConverter;
        this.notesConverter = notesConverter;
        this.ingredientConverter = ingredientConverter;
    }

    @Nullable
    @Synchronized
    @Override
    public RecipeCommand convert(Recipe source) {
        if(source == null) {
            return null;
        }

        final RecipeCommand recipeCommand = new RecipeCommand();

        recipeCommand.setId(source.getId());
        recipeCommand.setCookTime(source.getCookTime());
        recipeCommand.setDescription(source.getDescription());
        recipeCommand.setDifficulty(source.getDifficulty());
        recipeCommand.setDirections(source.getDirections());
        recipeCommand.setPrepTime(source.getPrepTime());
        recipeCommand.setServings(source.getServings());
        recipeCommand.setSource(source.getSource());
        recipeCommand.setUrl(source.getUrl());
        recipeCommand.setNotes(notesConverter.convert(source.getNotes()));

        if(source.getCategories() != null && source.getCategories().size() > 0) {
            source.getCategories().forEach((Category category) -> recipeCommand.getCategories()
            .add(categoryConverter.convert(category)));
        }

        if(source.getIngredients() != null && source.getIngredients().size() > 0) {
            source.getIngredients().forEach((Ingredient ingretient) -> recipeCommand.getIngredients()
            .add(ingredientConverter.convert(ingretient)));
        }

        return recipeCommand;
    }
}
