package pdi.project.com.example.recipeapi.service;

import org.apache.catalina.mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pdi.project.com.example.recipeapi.domain.Category;
import pdi.project.com.example.recipeapi.domain.Ingredient;
import pdi.project.com.example.recipeapi.domain.Instruction;
import pdi.project.com.example.recipeapi.domain.Recipe;
import pdi.project.com.example.recipeapi.dto.RecipeDTO;
import pdi.project.com.example.recipeapi.exception.CategoryInvalidExpection;
import pdi.project.com.example.recipeapi.exception.IngredientValidationException;
import pdi.project.com.example.recipeapi.exception.InstructionValidationException;
import pdi.project.com.example.recipeapi.exception.RecipeValidationException;
import pdi.project.com.example.recipeapi.repository.*;

import java.time.Instant;
import java.time.LocalTime;
import java.util.List;


import static java.util.Objects.isNull;

@Service
public class RecipeServiceV2 {

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private SubCategoryRepository subCategoryRepository;

    @Autowired
    private InstructionRepository instructionRepository;

    @Autowired
    private UnitMeasurementRepository unitMeasurementRepository;

    @Autowired
    private IngredientRepository ingredientRepository;

    public List<Recipe> findAll() {
        return recipeRepository.findAll();
    }

    public Recipe findById(Long id) {
        return recipeRepository.findById(id).get();
    }

    public Recipe insert(RecipeDTO recipeDTO) {

        validateRecipeDTO(recipeDTO);
        Recipe recipe = new Recipe();
        recipe.setName(recipeDTO.getName());
        recipe.setMoment(Instant.now());
        recipe.setCategory(categoryRepository.findById(recipeDTO.getCategoryId()).get());

        recipe.setSubCategory(subCategoryRepository.findById(recipeDTO.getSubCategoryId()).get());
        recipe.setYield(recipeDTO.getYield());
        recipe.setPrepareTime(preperTime(recipeDTO.getPrepareTime()));


        //Ingredient cannot be empty ou null
        for (int ing = 0; ing < recipeDTO.getIngredients().size(); ing++) {
            Ingredient ingredient = new Ingredient();
            ingredient.setName(recipeDTO.getIngredients().get(ing).getName());
            ingredient.setQuantity(recipeDTO.getIngredients().get(ing).getQuantity());
            ingredient.setUnitMeasurement(unitMeasurementRepository.findById(recipeDTO.getIngredients().get(ing).getUnit()).get());
            ingredientRepository.save(ingredient);
            recipe.getIngredients().add(ingredient);

        }

        //RecipeInstructions
        for (int i = 0; i < recipeDTO.getInstructions().size(); i++) {
            Instruction instruction = new Instruction();
            instruction.setInstruction(recipeDTO.getInstructions().get(i).getInstruction());
            instruction.setRecipe(recipe);
            instruction.setStep(i + 1);
            instructionRepository.save(instruction);
            recipe.getInstructions().add(instruction);

        }
        return recipeRepository.save(recipe);
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

    private void validateRecipeDTO(RecipeDTO recipeDTO) {
        if (isNull(recipeDTO)) {
            throw new RecipeValidationException("Recipe cannot be null");
        }
        if (isNull(recipeDTO.getName()) || recipeDTO.getName().isEmpty()) {
            throw new RecipeValidationException("The name of Recipe cannot be null or empty");
        }
        if (isNull(recipeDTO.getCategoryId()) || isNull(recipeDTO.getSubCategoryId())) {
            throw new CategoryInvalidExpection("Category or SubCategory id cannot be null");
        }
        if (isNull(recipeDTO.getInstructions()) || recipeDTO.getInstructions().isEmpty()) {
            throw new InstructionValidationException("Instructions cannot be null or empty");
        }
        if (isNull(recipeDTO.getIngredients()) || recipeDTO.getIngredients().isEmpty()) {
            throw new IngredientValidationException("Ingredient list cannot be empty");
        }

    }

}
