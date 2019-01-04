package com.rusinek.recipeapp.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class CategoryCommand {

    private Long Id;
    private String description;
}
