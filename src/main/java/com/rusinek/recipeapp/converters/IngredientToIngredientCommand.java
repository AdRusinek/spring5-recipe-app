package com.rusinek.recipeapp.converters;

import com.rusinek.recipeapp.commands.IngredientCommand;
import com.rusinek.recipeapp.domain.Ingredient;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class IngredientToIngredientCommand implements Converter<Ingredient, IngredientCommand> {

    private final UnitOfMeasureToUnitOfMeasureCommand uom;

    public IngredientToIngredientCommand(UnitOfMeasureToUnitOfMeasureCommand uom) {
        this.uom = uom;
    }

    @Nullable
    @Synchronized
    @Override
    public IngredientCommand convert(Ingredient source) {
        if(source == null) {
            return null;
        }

        IngredientCommand ingredientCommand = new IngredientCommand();

        ingredientCommand.setId(source.getId());
        ingredientCommand.setAmount(source.getAmount());
        ingredientCommand.setDescription(source.getDescription());
        ingredientCommand.setUnit(uom.convert(source.getUom()));
        if(source.getRecipe() != null) {
            ingredientCommand.setRecipeId(source.getRecipe().getId());
        }

        return ingredientCommand;
    }
}
