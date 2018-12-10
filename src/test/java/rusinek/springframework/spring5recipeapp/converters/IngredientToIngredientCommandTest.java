package rusinek.springframework.spring5recipeapp.converters;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import rusinek.springframework.spring5recipeapp.commands.IngredientCommand;
import rusinek.springframework.spring5recipeapp.domain.Ingredient;
import rusinek.springframework.spring5recipeapp.domain.Recipe;
import rusinek.springframework.spring5recipeapp.domain.UnitOfMeasure;

import java.math.BigDecimal;

import static org.junit.Assert.*;

class IngredientToIngredientCommandTest {


    public static final Recipe RECIPE = new Recipe();
    public static final BigDecimal AMOUNT = new BigDecimal("1");
    public static final String DESCRIPTION = "Cheesburger";
    public static final Long UOM_ID = new Long(2L);
    public static final Long ID_VALUE = new Long(1L);

    IngredientToIngredientCommand converter;

    @BeforeEach
    void setUp() {
        converter = new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand());
    }

    @Test
    void testNullConvert() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    void testEmptyObject() throws Exception {
        assertNotNull(converter.convert(new Ingredient()));
    }

    @Test
    void testsConvertNullUOM() throws Exception {
        //given
        Ingredient ingredient = new Ingredient();
        ingredient.setId(ID_VALUE);
        ingredient.setAmount(AMOUNT);
        ingredient.setDescription(DESCRIPTION);
        ingredient.setUom(null);
        //when
        IngredientCommand ingredientCommand = converter.convert(ingredient);
        //then
        assertNull(ingredientCommand.getUnitOfMeasure());
        assertEquals(ID_VALUE,ingredientCommand.getId());
        assertEquals(AMOUNT,ingredientCommand.getAmount());
        assertEquals(DESCRIPTION,ingredientCommand.getDescription());
    }

    @Test
    void testConvertWithUOM() throws Exception {
        //given
        Ingredient ingredient = new Ingredient();
        ingredient.setId(ID_VALUE);
        ingredient.setAmount(AMOUNT);
        ingredient.setDescription(DESCRIPTION);
        ingredient.setRecipe(RECIPE);

        UnitOfMeasure uom = new UnitOfMeasure();
        uom.setId(UOM_ID);

        ingredient.setUom(uom);
        //when
        IngredientCommand command = converter.convert(ingredient);
        //then
        assertEquals(ID_VALUE,command.getId());
        assertNotNull(command.getUnitOfMeasure());
        assertEquals(DESCRIPTION,command.getDescription());
        assertEquals(UOM_ID,command.getUnitOfMeasure().getId());
        assertEquals(AMOUNT,command.getAmount());
    }

}