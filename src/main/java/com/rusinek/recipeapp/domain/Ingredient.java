package com.rusinek.recipeapp.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@EqualsAndHashCode(exclude = {"recipe"})
@Data
public class Ingredient {

    public Ingredient() {
    }

    public Ingredient(String description, BigDecimal amount, UnitOfMeasure uom) {
        this.description = description;
        this.amount = amount;
        this.uom = uom;
    }

    public Ingredient(String description, BigDecimal amount, UnitOfMeasure uom, Recipe recipe) {
        this.description = description;
        this.amount = amount;
        this.uom = uom;
        this.recipe = recipe;
    }

    @ManyToOne
    private Recipe recipe;

    @OneToOne(fetch = FetchType.EAGER)
    private UnitOfMeasure uom;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String description;
    private BigDecimal amount;

}
