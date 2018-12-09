package rusinek.springframework.spring5recipeapp.converters;

import jdk.internal.jline.internal.Nullable;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import rusinek.springframework.spring5recipeapp.commands.UnitOfMeasureCommand;
import rusinek.springframework.spring5recipeapp.domain.UnitOfMeasure;

@Component
public class UnitOfMeasureCommandToUnitOfMeasure implements Converter<UnitOfMeasureCommand, UnitOfMeasure> {

    @Nullable
    @Synchronized
    @Override
    public UnitOfMeasure convert(UnitOfMeasureCommand source) {
        if(source == null) {
            return null;
        }

        final UnitOfMeasure unitOfMeasure = new UnitOfMeasure();

        unitOfMeasure.setDescription(source.getDescription());
        unitOfMeasure.setId(source.getId());

        return unitOfMeasure;
    }
}
