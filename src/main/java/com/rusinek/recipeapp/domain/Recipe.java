package com.rusinek.recipeapp.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class Recipe {

    @ManyToMany
    @JoinTable(name = "recipe_Category",
    joinColumns = @JoinColumn(name = "recipe_id"),inverseJoinColumns = @JoinColumn(name = "category_id"))
    Set<Category> categories = new HashSet<>();

    @OneToOne(cascade = CascadeType.ALL)
    private Notes notes;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "recipe")
    private Set<Ingredient> ingredients = new HashSet<>();

    // ze stringiem jest lepiej bo potem jak sie cos doda to nie zepsuje sie kolejnosc(a tak by bylo przy defaultowej wartosci)
    @Enumerated(value = EnumType.STRING)
    private Difficulty difficulty;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String description;
    private Integer prepTime;
    private Integer cookTime;
    private Integer servings;
    private String source;
    private String url;

    @Lob
    private String directions;

    @Lob
    private Byte[] image;

    public Recipe addIngredient(Ingredient ingredient) {
        ingredient.setRecipe(this);
        this.ingredients.add(ingredient);
        return this;
    }

    public void setNotes(Notes notes) {
        if (notes != null) {
            this.notes = notes;
            notes.setRecipe(this);
        }
    }
}
