package rusinek.springframework.spring5recipeapp.domain;

import javax.persistence.*;

@Entity
public class Notes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // In this one we don't have to specify cascade because this instance we are going to allow
    // the recipe to own this.  So if we delete the Notes object  we don't want to go back and delete
    // the recipe object / inverse if we want to delete recipe, of course we want to delete the recipe notes.
    @OneToOne
    private Recipe recipe;

    // doing this against String JPA is going to expect a distort in a clob field int the DB (This annotation
    // allow us to use String that a lot bigger than 255 characters )
    @Lob
    private String recipeNotes;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public String getRecipeNotes() {
        return recipeNotes;
    }

    public void setRecipeNotes(String recipeNotes) {
        this.recipeNotes = recipeNotes;
    }
}
