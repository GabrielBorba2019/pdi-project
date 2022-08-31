package pdi.project.com.example.recipeapi.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pdi.project.com.example.recipeapi.domain.*;
import pdi.project.com.example.recipeapi.domain.Ingredient;
import pdi.project.com.example.recipeapi.domain.Recipe;
import pdi.project.com.example.recipeapi.domain.StepInstruction;
import pdi.project.com.example.recipeapi.dto.IngredientDTO;
import pdi.project.com.example.recipeapi.dto.RecipeDTO;
import pdi.project.com.example.recipeapi.dto.StepInstructionDTO;
import pdi.project.com.example.recipeapi.exception.CategoryInvalidExpection;
import pdi.project.com.example.recipeapi.exception.RecipeValidationException;
import pdi.project.com.example.recipeapi.repository.CategoryRepository;
import pdi.project.com.example.recipeapi.repository.RecipeRepository;
import pdi.project.com.example.recipeapi.repository.SubCategoryRepository;

class RecipeServiceTest {

  private RecipeRepository recipeRepository = Mockito.mock(RecipeRepository.class);
  private CategoryRepository categoryRepository = Mockito.mock(CategoryRepository.class);
  private SubCategoryRepository subCategoryRepository = Mockito.mock(SubCategoryRepository.class);

  private IngredientService ingredientService = Mockito.mock(IngredientService.class);
  private StepInstructionService stepInstructionService =
      Mockito.mock(StepInstructionService.class);

  private RecipeService service =
      new RecipeService(
          recipeRepository,
          categoryRepository,
          subCategoryRepository,
          stepInstructionService,
          ingredientService);

  private final IngredientType GRAO_TYPE = new IngredientType(1L, "Grão");
  private final IngredientType CARNE_TYPE = new IngredientType(2L, "Carne");
  private final UnitOfMensurement UNIDADE = new UnitOfMensurement(1L, "Unidade");
  private final UnitOfMensurement XICARA = new UnitOfMensurement(2L, "Xícara");

  private final StepInstruction FIRST_STEP =
      new StepInstruction(0l, 1, "First instruction description");
  private final StepInstruction EMPTY_STEP = new StepInstruction(2l, 3, "");
  private final StepInstruction NULL_STEP = new StepInstruction(null);

  private final Ingredient INGREDIENT = new Ingredient(1L, "Lombo suíno", CARNE_TYPE, UNIDADE, "1");

  private final Category MEAT_CATEGORY = new Category(1l, "carne");
  private final SubCategory PORK_SUBCATEGORY = new SubCategory(1L, "suína");

  private final Recipe cakeRecipe =
      new Recipe(
          1L,
          null,
          MEAT_CATEGORY,
          PORK_SUBCATEGORY,
          List.of(INGREDIENT),
          List.of(FIRST_STEP),
          null,
          2,
          null);

  @Test
  @DisplayName("Create recipe with null data")
  void createRecipeWithNullData() {
    RuntimeException exception =
        assertThrows(
            RecipeValidationException.class,
            () -> {
              service.addRecipe(null);
            });

    String errorMessage = exception.getMessage();

    assertEquals("Recipe cannot be null", errorMessage);
  }

  @Test
  @DisplayName("Create recipet without name")
  void createRecipeWithoutName() {
    RuntimeException exception =
        assertThrows(
            RecipeValidationException.class,
            () -> {
              var recipeDTO =
                  (mockRecipeDTO(
                      1l,
                      2l,
                      "00:40:00",
                      mockIngredientsDTO(CARNE_TYPE, UNIDADE),
                      mockInstructionDTO(FIRST_STEP)));
              recipeDTO.setName(null);
              service.addRecipe(recipeDTO);
            });

    String errorMessage = exception.getMessage();

    assertEquals("The name of Recipe cannot be null or empty", errorMessage);
  }

  @Test
  @DisplayName("Create recipet without category Id")
  void createRecipeWithoutCategoryId() {
    RuntimeException exception =
        assertThrows(
            CategoryInvalidExpection.class,
            () -> {
              service.addRecipe(
                  mockRecipeDTO(
                      null,
                      2L,
                      "00:40:00",
                      mockIngredientsDTO(CARNE_TYPE, UNIDADE),
                      mockInstructionDTO(FIRST_STEP)));
            });

    String errorMessage = exception.getMessage();

    assertEquals("Category or Subcategory id cannot be null", errorMessage);
  }

  @Test
  @DisplayName("Create recipe without subcategory Id")
  void createRecipeWithoutSubtegoryId() {
    RuntimeException exception =
        assertThrows(
            CategoryInvalidExpection.class,
            () -> {
              var recipe =
                  service.addRecipe(
                      mockRecipeDTO(
                          1L,
                          null,
                          "00:40:00",
                          mockIngredientsDTO(CARNE_TYPE, UNIDADE),
                          mockInstructionDTO(FIRST_STEP)));
            });

    String errorMessage = exception.getMessage();

    assertEquals("Category or Subcategory id cannot be null", errorMessage);
  }

  @Test
  @DisplayName("Create Recipe without preper time")
  void createRecipeWithoutPreperTime(){
    //Given
    var instructionDTO = mockInstructionDTO(FIRST_STEP);
    var ingredientDTO = mockIngredientsDTO(CARNE_TYPE, UNIDADE);


    RuntimeException exception = assertThrows(RecipeValidationException.class, () -> {
      var recipe = service.addRecipe(mockRecipeDTO(1L, 2L, null , ingredientDTO, instructionDTO));
    });

    String errorMessage = exception.getMessage();

    assertEquals("Preper time cannot be null or empty", errorMessage);
  }

  @Test
  @DisplayName("Create recipe")
  void createRecipeSucessfully() {
    // Given
    var instructionDTO = mockInstructionDTO(FIRST_STEP);
    var ingredientDTO = mockIngredientsDTO(CARNE_TYPE, UNIDADE);
    var recipeDTO = mockRecipeDTO(1l, 2l, "00:40:00", ingredientDTO, instructionDTO);

    // When
    var recipe = service.addRecipe(recipeDTO);
    when(recipeRepository.addRecipe(any())).thenReturn(recipe);

    // Then
    assertEquals(recipe, recipeRepository.addRecipe(recipe));
  }

  private RecipeDTO mockRecipeDTO(
      Long category,
      Long subCategory,
      String prepareTime,
      IngredientDTO ingredientDTO,
      StepInstructionDTO stepInstructionDTO) {
    var recipeDTO =
        new RecipeDTO(
            "Bolo de cenoura",
            category,
            subCategory,
            List.of(ingredientDTO),
            List.of(stepInstructionDTO),
            prepareTime,
            02);
    return recipeDTO;
  }

  private IngredientDTO mockIngredientsDTO(
      IngredientType ingredientType, UnitOfMensurement unitOfMensurement) {
    var ingredientDTO =
        new IngredientDTO("Arroz", ingredientType.getId(), unitOfMensurement.getId(), "10");
    return ingredientDTO;
  }

  private StepInstructionDTO mockInstructionDTO(StepInstruction stepInstruction) {
    var instructionDTO = new StepInstructionDTO(stepInstruction.getInstruction());
    return instructionDTO;
  }
}
