package pdi.project.com.example.recipeapi.service;

import static java.util.Objects.isNull;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import pdi.project.com.example.recipeapi.domain.Ingredient;
import pdi.project.com.example.recipeapi.domain.IngredientType;
import pdi.project.com.example.recipeapi.domain.UnitOfMensurement;
import pdi.project.com.example.recipeapi.dto.IngredientDTO;
import pdi.project.com.example.recipeapi.exception.IngredientValidationException;
import pdi.project.com.example.recipeapi.repository.IngredientRepository;
import pdi.project.com.example.recipeapi.repository.IngredientTypeRepository;
import pdi.project.com.example.recipeapi.repository.UnitMeasureRepository;

@Service
public class IngredientService {

  private final UnitMeasureRepository unitMeasureRepository;
  private final IngredientTypeRepository ingredientTypeRepository;
  private final IngredientRepository ingredientRepository;

  public IngredientService(
      UnitMeasureRepository unitMeasureRepository,
      IngredientTypeRepository ingredientTypeRepository,
      IngredientRepository ingredientRepository) {
    this.unitMeasureRepository = unitMeasureRepository;
    this.ingredientTypeRepository = ingredientTypeRepository;
    this.ingredientRepository = ingredientRepository;
  }

  public List<Ingredient> createIngredientList(List<IngredientDTO> ingredientsDTO) {
    if (isNull(ingredientsDTO) || ingredientsDTO.isEmpty())
      throw new IngredientValidationException("Ingredient list cannot be empty");

    List<Long> ingredientTypesIds =
        ingredientsDTO.stream().map(IngredientDTO::getType).distinct().collect(Collectors.toList());

    List<Long> unitMeasureIds =
        ingredientsDTO.stream().map(IngredientDTO::getUnit).distinct().collect(Collectors.toList());

    List<IngredientType> ingredientTypes = ingredientTypeRepository.findAllByIds(ingredientTypesIds);
    List<UnitOfMensurement> unitOfMensurements = unitMeasureRepository.findAllByIds(unitMeasureIds);

    List<Ingredient> ingredientList =
        ingredientsDTO.stream()
            .map(dto -> mapToEntity(dto, ingredientTypes, unitOfMensurements))
            .collect(Collectors.toList());

    return ingredientRepository.saveAll(ingredientList);
  }

  private Ingredient mapToEntity(
      IngredientDTO dto,
      List<IngredientType> ingredientTypes,
      List<UnitOfMensurement> unitOfMensurements) {
    IngredientType ingredientType =
        ingredientTypes.stream()
            .filter(type -> type.getId().equals(dto.getType()))
            .findFirst()
            .orElse(null);

    UnitOfMensurement unitOfMensurement =
        unitOfMensurements.stream()
            .filter(unit -> unit.getId().equals(dto.getUnit()))
            .findFirst()
            .orElse(null);

    validateIngredientDTO(dto, ingredientType, unitOfMensurement);

    return new Ingredient(0L, dto.getName(), ingredientType, unitOfMensurement, dto.getQuantity());
  }

  private void validateIngredientDTO(
      IngredientDTO dto, IngredientType ingredientType, UnitOfMensurement unitOfMensurement) {
    var ingredientTypeDoesNotExistsOnDataBase = !isNull(dto.getType()) && isNull(ingredientType);
    if (ingredientTypeDoesNotExistsOnDataBase)
      throw new IngredientValidationException(
          "O tipo de ingrediente com id  " + dto.getType() + " não existe na base de dados.");

    if (isNull(dto.getType()))
      throw new IngredientValidationException(
          "O tipo de ingrediente é obrigatório para o ingrediente " + dto.getName());

    var unitOfMensurementDoesNotExistsOnDataBase =
        !isNull(dto.getUnit()) && isNull(unitOfMensurement);
    if (unitOfMensurementDoesNotExistsOnDataBase)
      throw new IngredientValidationException(
          "A unidade de medida com id  " + dto.getType() + " não existe na base de dados.");

    if (isNull(dto.getUnit()))
      throw new IngredientValidationException(
          "A unidade de medida é obrigatória para o ingrediente " + dto.getName());
  }
}
