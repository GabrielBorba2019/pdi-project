package pdi.project.com.example.recipeapi.domain;

import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class IngredientType {

  private Long id;
  private String name;

  public IngredientType(Optional<IngredientType> byId) {}
}
