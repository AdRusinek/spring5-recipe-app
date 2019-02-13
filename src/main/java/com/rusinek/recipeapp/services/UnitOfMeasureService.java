package com.rusinek.recipeapp.services;

import com.rusinek.recipeapp.commands.UnitOfMeasureCommand;
import com.rusinek.recipeapp.domain.UnitOfMeasure;

import java.util.Set;

public interface UnitOfMeasureService {

    Set<UnitOfMeasureCommand> listAllUoms();
}
