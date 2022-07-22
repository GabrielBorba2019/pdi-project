package pdi.project.com.example.recipeapi.domain;

import java.sql.Time;
import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Recipe {

  private Long id;
  private String name;
  private Category category;
  private SubCategory subCategory;
  private List<Ingredient> ingredients;
  private List<StepInstruction> stepInstructions;
  private Time prepareTime;
  private Integer yield;
  private Date date;
}
