package pdi.project.com.example.recipeapi.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pdi.project.com.example.recipeapi.domain.Ingredient;
import pdi.project.com.example.recipeapi.domain.IngredientType;
import pdi.project.com.example.recipeapi.domain.UnitOfMensurement;
import pdi.project.com.example.recipeapi.dto.IngredientDTO;
import pdi.project.com.example.recipeapi.exception.IngredientValidationException;
import pdi.project.com.example.recipeapi.repository.IngredientRepository;
import pdi.project.com.example.recipeapi.repository.IngredientTypeRepository;
import pdi.project.com.example.recipeapi.repository.UnitMeasureRepository;

class IngredientServiceTest {

  private UnitMeasureRepository unitMeasureRepository = Mockito.mock(UnitMeasureRepository.class);
  private IngredientTypeRepository ingredientTypeRepository =
      Mockito.mock(IngredientTypeRepository.class);
  private IngredientRepository ingredientRepository = Mockito.mock(IngredientRepository.class);

  private IngredientService service =
      new IngredientService(unitMeasureRepository, ingredientTypeRepository, ingredientRepository);

  private final IngredientType GRAO_TYPE = new IngredientType(1L, "Grão");
  private final IngredientType CARNE_TYPE = new IngredientType(2L, "Carne");
  private final UnitOfMensurement UNIDADE = new UnitOfMensurement(1L, "Unidade");
  private final UnitOfMensurement XICARA = new UnitOfMensurement(2L, "Xícara");

  @Test
  @DisplayName("Create ingredients with empty ingredient list")
  void createIngredientsWithEmptyList() {
    RuntimeException exception =
        assertThrows(
            IngredientValidationException.class,
            () -> {
              service.createIngredientList(Collections.emptyList());
            });

    String errorMessage = exception.getMessage();

    assertEquals("Ingredient list cannot be empty", errorMessage);
  }

  @Test
  @DisplayName("Create ingredients with null ingredient list")
  void createIngredientsWithNullList() {
    RuntimeException exception =
        assertThrows(
            IngredientValidationException.class,
            () -> {
              service.createIngredientList(null);
            });

    String errorMessage = exception.getMessage();

    assertEquals("Ingredient list cannot be empty", errorMessage);
  }

  @Test
  @DisplayName("Create ingredients without ingredient type on database")
  void createIngredientsWithoutIngredientTypeOnDatabase() {
    // Given
    var ingredientsDto = mockIngredientsDTO(CARNE_TYPE, UNIDADE);

    // when
    when(ingredientTypeRepository.findAllById(any())).thenReturn(List.of(GRAO_TYPE));
    when(unitMeasureRepository.findAllById(any())).thenReturn(List.of(UNIDADE));

    // Then
    RuntimeException exception =
        assertThrows(
            IngredientValidationException.class,
            () -> {
              service.createIngredientList(List.of(ingredientsDto));
            });

    String errorMessage = exception.getMessage();

    assertEquals("O tipo de ingrediente com id  2 não existe na base de dados.", errorMessage);
  }

  @Test
  @DisplayName("Create ingredients with null ingredient type")
  void createIngredientsWithNullIngredientType() {
    // Given
    var ingredientsDto = mockIngredientsDTO(GRAO_TYPE, UNIDADE);
    ingredientsDto.setType(null);

    // when
    when(ingredientTypeRepository.findAllById(any())).thenReturn(List.of(GRAO_TYPE));
    when(unitMeasureRepository.findAllById(any())).thenReturn(List.of(UNIDADE));

    // Then
    RuntimeException exception =
        assertThrows(
            IngredientValidationException.class,
            () -> {
              service.createIngredientList(List.of(ingredientsDto));
            });

    String errorMessage = exception.getMessage();

    assertEquals("O tipo de ingrediente é obrigatório para o ingrediente Arroz", errorMessage);
  }

  @Test
  @DisplayName("Create ingredients without ingredient type on database")
  void createIngredientsWithoutUnitOnDatabase() {
    // Given
    var ingredientsDto = mockIngredientsDTO(GRAO_TYPE, UNIDADE);

    // when
    when(ingredientTypeRepository.findAllById(any())).thenReturn(List.of(GRAO_TYPE));
    when(unitMeasureRepository.findAllById(any())).thenReturn(List.of(XICARA));

    // Then
    RuntimeException exception =
        assertThrows(
            IngredientValidationException.class,
            () -> {
              service.createIngredientList(List.of(ingredientsDto));
            });

    String errorMessage = exception.getMessage();

    assertEquals("A unidade de medida com id  1 não existe na base de dados.", errorMessage);
  }

  @Test
  @DisplayName("Create ingredients with null unit mensurement")
  void createIngredientsWithNullUnitMensurement() {
    // Given
    var ingredientsDto = mockIngredientsDTO(GRAO_TYPE, UNIDADE);
    ingredientsDto.setUnit(null);

    // when
    when(ingredientTypeRepository.findAllById(any())).thenReturn(List.of(GRAO_TYPE));
    when(unitMeasureRepository.findAllById(any())).thenReturn(List.of(UNIDADE));

    // Then
    RuntimeException exception =
        assertThrows(
            IngredientValidationException.class,
            () -> {
              service.createIngredientList(List.of(ingredientsDto));
            });

    String errorMessage = exception.getMessage();

    assertEquals("A unidade de medida é obrigatória para o ingrediente Arroz", errorMessage);
  }

  @Test
  @DisplayName("Create ingredients successfully")
  void createIngredientsListSuccessfully() {
    // Given
    var ingredientsDto = mockIngredientsDTO(GRAO_TYPE, UNIDADE);
    var ingredient = new Ingredient(1L, ingredientsDto.getName(), GRAO_TYPE, UNIDADE, "10");

    // when
    when(ingredientTypeRepository.findAllById(any())).thenReturn(List.of(GRAO_TYPE));
    when(unitMeasureRepository.findAllById(any())).thenReturn(List.of(UNIDADE));
    when(ingredientRepository.saveAll(any())).thenReturn(List.of(ingredient));

    // Then

    var ingredients = service.createIngredientList(List.of(ingredientsDto));

    assertEquals(1, ingredients.size());
  }

  private IngredientDTO mockIngredientsDTO(
      IngredientType ingredientType, UnitOfMensurement unitOfMensurement) {
    var ingredientDTO =
        new IngredientDTO("Arroz", ingredientType.getId(), unitOfMensurement.getId(), "10");

    return ingredientDTO;
  }
}
