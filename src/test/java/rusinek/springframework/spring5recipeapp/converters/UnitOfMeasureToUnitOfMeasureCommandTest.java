package rusinek.springframework.spring5recipeapp.converters;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import rusinek.springframework.spring5recipeapp.commands.UnitOfMeasureCommand;
import rusinek.springframework.spring5recipeapp.domain.UnitOfMeasure;

import static org.junit.jupiter.api.Assertions.*;

class UnitOfMeasureToUnitOfMeasureCommandTest {

    public static final Long ID_VALUE = new Long(1L);
    public static final String DESCRIPTION = "Description";

    UnitOfMeasureToUnitOfMeasureCommand converter;

    @BeforeEach
    void setUp() {
        converter = new UnitOfMeasureToUnitOfMeasureCommand();
    }

    @Test
    public void testNullObjectConvert() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObj() throws Exception {
        assertNotNull(converter.convert(new UnitOfMeasure()));
    }

    @Test
    void convert() {
        //given
        UnitOfMeasure uom = new UnitOfMeasure();
        uom.setId(ID_VALUE);
        uom.setDescription(DESCRIPTION);

        //when
        UnitOfMeasureCommand command = converter.convert(uom);

        //then
        assertEquals(ID_VALUE,command.getId());
        assertEquals(DESCRIPTION,command.getDescription());
    }
}