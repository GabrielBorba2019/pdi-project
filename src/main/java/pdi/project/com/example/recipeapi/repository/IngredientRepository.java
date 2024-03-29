package pdi.project.com.example.recipeapi.repository;

import java.util.ArrayList;
import java.util.List;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import pdi.project.com.example.recipeapi.domain.Ingredient;

@Component
@Scope("singleton")
public class IngredientRepository {
  private final List<Ingredient> ingredients;

  public IngredientRepository() {
    ingredients = new ArrayList<>();
  }

  public List<Ingredient> saveAll(List<Ingredient> ingredients) {
    ingredients.forEach(
        ingredient -> {
          ingredient.setId((long) (this.ingredients.size() + 1));
          this.ingredients.add(ingredient);
        });

    return ingredients;
  }
}
