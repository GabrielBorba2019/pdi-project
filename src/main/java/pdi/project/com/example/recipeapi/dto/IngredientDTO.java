package pdi.project.com.example.recipeapi.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IngredientDTO {

  private String name;
  private Long type;
  private Long unit;
  private String quantity;
}
