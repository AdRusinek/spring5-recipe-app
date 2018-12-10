package rusinek.springframework.spring5recipeapp.converters;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import rusinek.springframework.spring5recipeapp.commands.NotesCommand;
import rusinek.springframework.spring5recipeapp.domain.Notes;

import static org.junit.Assert.*;

class NotesToNotesCommandTest {

    public static final Long ID_VALUE = 1L;
    public static final String RECIPE_NOTES = "Nots";
    NotesToNotesCommand converter;

    @BeforeEach
    void setUp() {
        converter = new NotesToNotesCommand();
    }

    @Test
    void convert() {
        //given
        Notes notes = new Notes();
        notes.setId(ID_VALUE);
        notes.setRecipeNotes(RECIPE_NOTES);

        //when
        NotesCommand command = converter.convert(notes);

        //then
        assertEquals(ID_VALUE,command.getId());
        assertEquals(RECIPE_NOTES,command.getRecipeNotes());
    }

    @Test
    void testNull() {
        assertNull(converter.convert(null));
    }

    @Test
    void testEmptyObject() {
        assertNotNull(converter.convert(new Notes()));
    }
}