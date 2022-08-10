package pdi.project.com.example.recipeapi.service;

import org.springframework.stereotype.Service;
import pdi.project.com.example.recipeapi.domain.Ingredient;
import pdi.project.com.example.recipeapi.dto.IngredientDTO;
import pdi.project.com.example.recipeapi.dto.RecipeDTO;
import pdi.project.com.example.recipeapi.repository.IngredientTypeRepository;
import pdi.project.com.example.recipeapi.repository.UnitMeasureRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class IngredientService {

    private final UnitMeasureRepository unitMeasureRepository;
    private final IngredientTypeRepository ingredientTypeRepository;


    public IngredientService(UnitMeasureRepository unitMeasureRepository, IngredientTypeRepository ingredientTypeRepository) {
        this.unitMeasureRepository = unitMeasureRepository;
        this.ingredientTypeRepository = ingredientTypeRepository;
    }

    public List<Ingredient> createIngredientList(List<IngredientDTO> ingredientsDTO){
        List<Ingredient> ingredientList = ingredientsDTO.stream().map(
                x -> new Ingredient(x.getName()
                        ,ingredientTypeRepository.findById(x.getType())
                        ,unitMeasureRepository.findById(x.getUnit())
                        ,x.getQuantity())
        ).collect(Collectors.toList());

        return ingredientList;
    }
}
