package com.rusinek.recipeapp.converters;

import com.rusinek.recipeapp.commands.IngredientCommand;
import com.rusinek.recipeapp.commands.UnitOfMeasureCommand;
import com.rusinek.recipeapp.domain.Ingredient;
import com.rusinek.recipeapp.domain.Recipe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class IngredientCommandToIngredientTest {

    public static final Recipe RECIPE = new Recipe();
    public static final BigDecimal AMOUNT = new BigDecimal("1");
    public static final String DESCRIPTION = "Cheesburger";
    public static final Long ID_VALUE = new Long(1L);
    public static final Long UOM_ID = new Long(2L);

    IngredientCommandToIngredient converter;

    @BeforeEach
    void setUp() throws Exception {
        converter = new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure());
    }

    @Test
    public void testNullObject() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() throws Exception {
        assertNotNull(converter.convert(new IngredientCommand()));
    }

    @Test
    void convert() throws Exception {
        //given
        IngredientCommand command = new IngredientCommand();
        command.setId(ID_VALUE);
        command.setAmount(AMOUNT);
        command.setDescription(DESCRIPTION);
        UnitOfMeasureCommand unitOfMeasureCommand = new UnitOfMeasureCommand();
        unitOfMeasureCommand.setId(UOM_ID);
        command.setUnit(unitOfMeasureCommand);

        //when
        Ingredient ingredient = converter.convert(command);


        //then
        assertNotNull(ingredient);
        assertNotNull(ingredient.getUom());
        assertEquals(ID_VALUE,ingredient.getId());
        assertEquals(AMOUNT,ingredient.getAmount());
        assertEquals(DESCRIPTION,ingredient.getDescription());
        assertEquals(UOM_ID,ingredient.getUom().getId());

    }

    public void convertWithNullUOM() throws Exception {

        // given
        IngredientCommand command = new IngredientCommand();
        command.setId(ID_VALUE);
        command.setAmount(AMOUNT);
        command.setDescription(DESCRIPTION);

        UnitOfMeasureCommand unitOfMeasureCommand = new UnitOfMeasureCommand();

        // when
        Ingredient ingredient = converter.convert(command);

        //then
        assertNotNull(ingredient);
        assertNull(ingredient.getId());
        assertEquals(ID_VALUE,ingredient.getId());
        assertEquals(DESCRIPTION,ingredient.getDescription());
        assertEquals(AMOUNT,ingredient.getAmount());




    }
}