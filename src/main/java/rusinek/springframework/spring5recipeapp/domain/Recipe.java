package rusinek.springframework.spring5recipeapp.domain;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Recipe {


    // this is going to leverage the underlying persistence framework to generate ID value for us
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private Integer prepTime;
    private Integer cookTime;
    private Integer servings;
    private String source;
    private String url;
    private String directions;
    // todo add
    // private Difficulty difficulty

    // we are saying that this recipe will get stored on a property on the set of ingredients on each object
    // is going to be a property called recipe. So we are getting set of ingredients coming back
    // I used set because I will have unique set of Ingredients
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "recipe")
    private Set<Ingredient> ingredients;

    // table recipe_category, from first direction it uses recipe_id and coming back I have category_id
    // creating recipe-category table in hibernate, and from this side of the relationship i have join column
    // called recipe_id and ont the other side for the categories I have category_id
    @ManyToMany
    @JoinTable(name = "recipe_category",
        joinColumns =  @JoinColumn(name = "recipe_id"),
        inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Category> categories;

    // "binary large object"
    @Lob
    private Byte[] image;

    // There is ORDINAL and STRING (ORDINAL is default so if this will not be specified JPA will take this one)
    // this will not help if new enum type in the future will be added because it will change the order
    // and mess up our DB.
    // STRING overrides it
    @Enumerated(value = EnumType.STRING)
    private Difficulty difficulty;

    // that defines relationship and this is Owner, so if we delete recipe that is going to persist down and
    // delete notes.
    @OneToOne(cascade = CascadeType.ALL)
    private Notes notes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPrepTime() {
        return prepTime;
    }

    public void setPrepTime(Integer prepTime) {
        this.prepTime = prepTime;
    }

    public Integer getCookTime() {
        return cookTime;
    }

    public void setCookTime(Integer cookTime) {
        this.cookTime = cookTime;
    }

    public Integer getServings() {
        return servings;
    }

    public void setServings(Integer servings) {
        this.servings = servings;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDirections() {
        return directions;
    }

    public void setDirections(String directions) {
        this.directions = directions;
    }

    public Byte[] getImage() {
        return image;
    }

    public void setImage(Byte[] image) {
        this.image = image;
    }

    public Notes getNotes() {
        return notes;
    }

    public void setNotes(Notes notes) {
        this.notes = notes;
    }

    public Set<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Set<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }
}