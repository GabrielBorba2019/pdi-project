package pdi.project.com.example.recipeapi.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StepInstruction {

  private Long id;
  private Integer step;
  private String instruction;

}
