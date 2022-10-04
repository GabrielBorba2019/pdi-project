package pdi.project.com.example.recipeapi.mappers;

import pdi.project.com.example.recipeapi.domain.*;
import pdi.project.com.example.recipeapi.dto.RecipeDTO;
import pdi.project.com.example.recipeapi.exception.RecipeValidationException;

import java.sql.Timestamp;
import java.time.LocalTime;
import java.util.List;

import static java.util.Objects.isNull;

public class MapperToRecipe implements Mapper {


    @Override
    public Recipe toRecipe(RecipeDTO recipeDTO, List<Ingredient> ingredients,
                           List<Instruction> instructions) {
        return null;
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
