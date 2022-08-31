package pdi.project.com.example.recipeapi.service;

import static java.util.Objects.isNull;

import java.sql.Timestamp;
import java.time.LocalTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pdi.project.com.example.recipeapi.domain.Recipe;
import pdi.project.com.example.recipeapi.dto.RecipeDTO;
import pdi.project.com.example.recipeapi.exception.CategoryInvalidExpection;
import pdi.project.com.example.recipeapi.exception.RecipeValidationException;
import pdi.project.com.example.recipeapi.repository.*;

@Service
public class RecipeService {

  private final RecipeRepository recipeRepository;
  private final CategoryRepository categoryRepository;
  private final SubCategoryRepository subCategoryRepository;
  private final StepInstructionService stepInstructionService;
  private final IngredientService ingredientService;

  @Autowired
  public RecipeService(
      RecipeRepository recipeRepository,
      CategoryRepository categoryRepository,
      SubCategoryRepository subCategoryRepository,
      StepInstructionService stepInstructionService,
      IngredientService ingredientService) {
    this.recipeRepository = recipeRepository;
    this.categoryRepository = categoryRepository;
    this.subCategoryRepository = subCategoryRepository;
    this.stepInstructionService = stepInstructionService;
    this.ingredientService = ingredientService;
  }

  public Recipe addRecipe(RecipeDTO recipeDTO) {

    if (isNull(recipeDTO)) {
      throw new RecipeValidationException("Recipe cannot be null");
    }
    Recipe recipe = new Recipe();

    if (isNull(recipeDTO.getCategoryId()) || isNull(recipeDTO.getSubCategoryId())) {
      throw new CategoryInvalidExpection("Category or Subcategory id cannot be null");
    }
    categoryRepository.findById(recipeDTO.getCategoryId()).ifPresent(recipe::setCategory);
    subCategoryRepository.findById(recipeDTO.getSubCategoryId()).ifPresent(recipe::setSubCategory);

    recipe.setIngredients(ingredientService.createIngredientList(recipeDTO.getIngredients()));

    if (isNull(recipeDTO.getName()) || recipeDTO.getName().isEmpty()) {
      throw new RecipeValidationException("The name of Recipe cannot be null or empty");
    }
    recipe.setName(recipeDTO.getName());
    recipe.setPrepareTime(preperTime(recipeDTO.getPrepareTime()));
    recipe.setStepInstructions(
        stepInstructionService.createInstructions(recipeDTO.getStepInstructions()));
    recipe.setYield(4);
    recipe.setDate(new Timestamp(System.currentTimeMillis()));
    recipeRepository.addRecipe(recipe);

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
