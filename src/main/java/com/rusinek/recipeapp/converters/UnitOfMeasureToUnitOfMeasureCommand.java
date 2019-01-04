package com.rusinek.recipeapp.converters;

import com.rusinek.recipeapp.commands.UnitOfMeasureCommand;
import com.rusinek.recipeapp.domain.UnitOfMeasure;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class UnitOfMeasureToUnitOfMeasureCommand implements Converter<UnitOfMeasure, UnitOfMeasureCommand> {

    @Override
    @Nullable
    @Synchronized
    public UnitOfMeasureCommand convert(UnitOfMeasure source) {
        if(source == null) {
            return null;
        }

        UnitOfMeasureCommand uomCommand = new UnitOfMeasureCommand();

        uomCommand.setDescription(source.getDescription());
        uomCommand.setId(source.getId());

        return uomCommand;
    }
}
