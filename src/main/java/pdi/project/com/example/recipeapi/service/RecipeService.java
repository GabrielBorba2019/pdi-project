package pdi.project.com.example.recipeapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;
import pdi.project.com.example.recipeapi.domain.Ingredient;
import pdi.project.com.example.recipeapi.domain.Recipe;
import pdi.project.com.example.recipeapi.domain.StepInstruction;
import pdi.project.com.example.recipeapi.dto.RecipeDTO;
import pdi.project.com.example.recipeapi.dto.StepInstructionDTO;
import pdi.project.com.example.recipeapi.repository.*;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class RecipeService {

    private final RecipeRepository recipeRepository;
    private final CategoryRepository categoryRepository;
    private final SubCategoryRepository subCategoryRepository;
    private final StepInstructionService stepInstructionService;
    private final IngredientService ingredientService;


    @Autowired
    public RecipeService(RecipeRepository recipeRepository, CategoryRepository categoryRepository, SubCategoryRepository subCategoryRepository, StepInstructionService stepInstructionService, IngredientService ingredientService) {
        this.recipeRepository = recipeRepository;
        this.categoryRepository = categoryRepository;
        this.subCategoryRepository = subCategoryRepository;
        this.stepInstructionService = stepInstructionService;
        this.ingredientService = ingredientService;

    }

    public Recipe addRecipe(RecipeDTO recipeDTO) {

        Recipe recipe = new Recipe();
        categoryRepository.findById(recipeDTO.getCategoryId()).ifPresent(recipe::setCategory);
        subCategoryRepository.findById(recipeDTO.getSubCategoryId()).ifPresent(recipe::setSubCategory);
        recipe.setIngredients(ingredientService.createIngredientList(recipeDTO.getIngredients()));
        recipe.setName(recipeDTO.getName());
        recipe.setPrepareTime(preperTime(recipeDTO.getPrepareTime()));
        recipe.setStepInstructions(stepInstructionService.createInstructions(recipeDTO.getStepInstructions()));
        recipe.setYield(4);
        recipe.setDate(new Timestamp(System.currentTimeMillis()));
        recipeRepository.addRecipe(recipe);
        return recipe;
    }

    private LocalTime preperTime(String prepareTime) {
        String array[] = prepareTime.split(":", 3);
        int hour = Integer.parseInt(array[0]);
        int minute = Integer.parseInt(array[1]);
        int second = Integer.parseInt(array[2]);

        LocalTime preperDuration = LocalTime.of(hour, minute, second);

        return preperDuration;
    }

}

