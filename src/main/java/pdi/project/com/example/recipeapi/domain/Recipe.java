package pdi.project.com.example.recipeapi.domain;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Recipe {

  private Long id;
  private String name;
  private Category category;
  private SubCategory subCategory;
  private List<Ingredient> ingredients;
  private List<StepInstruction> stepInstructions;
  private LocalTime prepareTime;
  private Integer yield;
  private Date date;
}
