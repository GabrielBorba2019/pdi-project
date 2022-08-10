package pdi.project.com.example.recipeapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecipeDTO {

    private String name;
    private Long categoryId;
    private Long subCategoryId;
    private List<IngredientDTO> ingredients;
    private List<StepInstructionDTO> stepInstructions;
    private String prepareTime;
    private Integer yield;

}
