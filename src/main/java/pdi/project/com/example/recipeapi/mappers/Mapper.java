package pdi.project.com.example.recipeapi.mappers;

import pdi.project.com.example.recipeapi.domain.Ingredient;
import pdi.project.com.example.recipeapi.domain.Recipe;
import pdi.project.com.example.recipeapi.domain.Instruction;
import pdi.project.com.example.recipeapi.dto.RecipeDTO;

import java.util.List;

public interface Mapper {

    Recipe toRecipe(RecipeDTO recipeDto, List<Ingredient> ingredients,
                    List<Instruction> instructions);


}
