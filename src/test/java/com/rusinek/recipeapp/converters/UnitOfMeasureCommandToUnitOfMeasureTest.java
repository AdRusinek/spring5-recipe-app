package com.rusinek.recipeapp.converters;

import com.rusinek.recipeapp.commands.UnitOfMeasureCommand;
import com.rusinek.recipeapp.domain.UnitOfMeasure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UnitOfMeasureCommandToUnitOfMeasureTest {

    public static final Long ID_VALUE = 1L;
    public static final String DESCRIPTION = "Description";

    UnitOfMeasureCommandToUnitOfMeasure converter;

    @BeforeEach
    void setUp() {
        converter = new UnitOfMeasureCommandToUnitOfMeasure();
    }

    @Test
    void testNullParamter() {
        assertNull(converter.convert(null));
    }

    @Test
    void testEmptyObject() {
        assertNotNull(converter.convert(new UnitOfMeasureCommand()));
    }

    @Test
    void convert() {
        //given
        UnitOfMeasureCommand command = new UnitOfMeasureCommand();
        command.setId(ID_VALUE);
        command.setDescription(DESCRIPTION);

        //when
        UnitOfMeasure unitOfMeasure = converter.convert(command);

        assertNotNull(unitOfMeasure);
        assertEquals(ID_VALUE,unitOfMeasure.getId());
        assertEquals(DESCRIPTION,unitOfMeasure.getDescription());
    }
}