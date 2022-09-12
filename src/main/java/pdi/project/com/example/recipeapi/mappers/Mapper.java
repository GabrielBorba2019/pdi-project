package pdi.project.com.example.recipeapi.mappers;

import java.util.List;
import pdi.project.com.example.recipeapi.domain.Ingredient;
import pdi.project.com.example.recipeapi.domain.Recipe;
import pdi.project.com.example.recipeapi.domain.StepInstruction;
import pdi.project.com.example.recipeapi.dto.RecipeDTO;

public interface Mapper {

  Recipe toRecipe(
      RecipeDTO recipeDto, List<Ingredient> ingredients, List<StepInstruction> stepInstructions);
}
