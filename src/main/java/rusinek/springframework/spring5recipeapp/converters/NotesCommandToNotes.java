package rusinek.springframework.spring5recipeapp.converters;

import jdk.internal.jline.internal.Nullable;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import rusinek.springframework.spring5recipeapp.commands.NotesCommand;
import rusinek.springframework.spring5recipeapp.domain.Notes;

@Component
public class NotesCommandToNotes implements Converter<NotesCommand, Notes> {

    @Synchronized
    @Nullable
    @Override
    public Notes convert(NotesCommand source) {
        if(source == null) {
            return null;
        }

        Notes notes = new Notes();
        notes.setId(source.getId());
        notes.setRecipeNotes(source.getRecipeNotes());

        return notes;
    }
}
