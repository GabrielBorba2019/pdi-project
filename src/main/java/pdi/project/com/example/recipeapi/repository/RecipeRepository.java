package pdi.project.com.example.recipeapi.repository;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import pdi.project.com.example.recipeapi.domain.Recipe;
import pdi.project.com.example.recipeapi.domain.UnitOfMensurement;
import pdi.project.com.example.recipeapi.dto.RecipeDTO;

import java.util.ArrayList;
import java.util.List;

@Component
@Scope("singleton")
public class RecipeRepository {
    private final List<Recipe> recipes;

    public RecipeRepository(){
        recipes = new ArrayList<>();
    }

    public Recipe addRecipe(Recipe recipe){
        long id = recipes.size() + 1;

        recipe.setId(id);
        recipes.add(recipe);

        return recipe;
    }
}
