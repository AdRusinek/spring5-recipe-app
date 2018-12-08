package rusinek.springframework.spring5recipeapp.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class Recipe {


    // this is going to leverage the underlying persistence framework to generate ID value for us
    // Only a test
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private Integer prepTime;
    private Integer cookTime;
    private Integer servings;
    private String source;
    private String url;
    @Lob
    private String directions;
    // todo add
    // private Difficulty difficulty

    // we are saying that this recipe will get stored on a property on the set of ingredients on each object
    // is going to be a property called recipe. So we are getting set of ingredients coming back
    // I used set because I will have unique set of Ingredients
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "recipe")
    // we are creating a new instance to avoid null pointer exception
    private Set<Ingredient> ingredients = new HashSet<>();

    // table recipe_category, from first direction it uses recipe_id and coming back I have category_id
    // creating recipe-category table in hibernate, and from this side of the relationship i have join column
    // called recipe_id and ont the other side for thee categories I have category_id
    @ManyToMany
    @JoinTable(name = "recipe_category",
            joinColumns = @JoinColumn(name = "recipe_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Category> categories = new HashSet<>();

    // "binary large object"
    @Lob
    private Byte[] image;

    // There is ORDINAL and STRING (ORDINAL is default so if this will not be specified JPA w   ill take this one)
    // this will not help if new enum type in the future will be added because it will change the order
    // and mess up our DB.
    // STRING overrides it
    @Enumerated(value = EnumType.STRING)
    private Difficulty difficulty;

    // that defines relationship and this is Owner, so if we delete recipe that is going to persist down and
    // delete notes.
    @OneToOne(cascade = CascadeType.ALL)
    private Notes notes;

    // convinience method but it also encapsulate logic in one spot
    public Recipe addIngredient(Ingredient ingredient) {
        ingredient.setRecipe(this);
        this.ingredients.add(ingredient);
        return this;
    }

    public void setNotes(Notes notes) {
        this.notes = notes;
        // we built an association
        notes.setRecipe(this);
    }

}
