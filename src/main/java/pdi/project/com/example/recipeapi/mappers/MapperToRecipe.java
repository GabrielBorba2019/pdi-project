package pdi.project.com.example.recipeapi.mappers;

import static java.util.Objects.isNull;

import java.sql.Timestamp;
import java.time.LocalTime;
import java.util.List;
import pdi.project.com.example.recipeapi.domain.*;
import pdi.project.com.example.recipeapi.dto.RecipeDTO;
import pdi.project.com.example.recipeapi.exception.RecipeValidationException;

public class MapperToRecipe implements Mapper {

  @Override
  public Recipe toRecipe(
      RecipeDTO recipeDTO, List<Ingredient> ingredients, List<StepInstruction> stepInstructions) {
    Recipe recipe = new Recipe();
    recipe.setIngredients(ingredients);
    recipe.setName(recipeDTO.getName());
    recipe.setPrepareTime(preperTime(recipeDTO.getPrepareTime()));
    recipe.setStepInstructions(stepInstructions);
    recipe.setYield(4);
    recipe.setDate(new Timestamp(System.currentTimeMillis()));

    return recipe;
  }

  private LocalTime preperTime(String prepareTime) {

    if (isNull(prepareTime) || prepareTime.isEmpty()) {
      throw new RecipeValidationException("Preper time cannot be null or empty");
    }

    String array[] = prepareTime.split(":", 3);
    int hour = Integer.parseInt(array[0]);
    int minute = Integer.parseInt(array[1]);
    int second = Integer.parseInt(array[2]);

    LocalTime preperDuration = LocalTime.of(hour, minute, second);

    return preperDuration;
  }
}
