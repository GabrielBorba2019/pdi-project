package pdi.project.com.example.recipeapi.service;

import static java.util.Objects.isNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pdi.project.com.example.recipeapi.domain.Recipe;
import pdi.project.com.example.recipeapi.dto.RecipeDTO;
import pdi.project.com.example.recipeapi.exception.CategoryInvalidExpection;
import pdi.project.com.example.recipeapi.exception.RecipeValidationException;
import pdi.project.com.example.recipeapi.mappers.MapperToRecipe;
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
        validateRecipeDTO(recipeDTO);

        var ingredients = ingredientService.createIngredientList(recipeDTO.getIngredients());
        var stepInstructions = stepInstructionService.createInstructions(recipeDTO.getStepInstructions());
        var recipe = new MapperToRecipe().toRecipe(recipeDTO, ingredients, stepInstructions);

        categoryRepository.findById(recipeDTO.getCategoryId()).ifPresent(recipe::setCategory);
        subCategoryRepository.findById(recipeDTO.getSubCategoryId()).ifPresent(recipe::setSubCategory);
        recipeRepository.addRecipe(recipe);

        return recipe;
    }

    private void validateRecipeDTO(RecipeDTO recipeDTO) {
        if (isNull(recipeDTO)) {
            throw new RecipeValidationException("Recipe cannot be null");
        }
        if (isNull(recipeDTO.getName()) || recipeDTO.getName().isEmpty()) {
            throw new RecipeValidationException("The name of Recipe cannot be null or empty");
        }
        if (isNull(recipeDTO.getCategoryId()) || isNull(recipeDTO.getSubCategoryId())) {
            throw new CategoryInvalidExpection("Category or Subcategory id cannot be null");
        }
    }
}
