package com.rusinek.recipeapp.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class UnitOfMeasure {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
}
