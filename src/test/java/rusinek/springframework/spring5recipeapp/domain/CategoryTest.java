package rusinek.springframework.spring5recipeapp.domain;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CategoryTest {

    Category category;

    @Before // this create Category obj before each test method is executed
    public void setUp() {
        category = new Category();
    }

    @Test
    public void getId() {
        Long idValue = 4L;

        category.setId(idValue);

        // expected value / value
        assertEquals(idValue, category.getId());
    }

    @Test
    public void getDescription() {

    }

    @Test
    public void getRecipes() {
    }
}