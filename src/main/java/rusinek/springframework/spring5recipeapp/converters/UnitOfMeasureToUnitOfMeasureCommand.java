package rusinek.springframework.spring5recipeapp.converters;

import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import rusinek.springframework.spring5recipeapp.commands.UnitOfMeasureCommand;
import rusinek.springframework.spring5recipeapp.domain.UnitOfMeasure;

@Component
public class UnitOfMeasureToUnitOfMeasureCommand implements Converter<UnitOfMeasure, UnitOfMeasureCommand> {

    @Synchronized
    @Nullable
    @Override
    public UnitOfMeasureCommand convert(UnitOfMeasure source) {
        if(source == null) {
            return null;
        }

        final UnitOfMeasureCommand uomConverter = new UnitOfMeasureCommand();

        uomConverter.setId(source.getId());
        uomConverter.setDescription(source.getDescription());

        return uomConverter;
    }
}
