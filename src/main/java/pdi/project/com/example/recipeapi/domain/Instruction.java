package pdi.project.com.example.recipeapi.domain;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "instruction")
public class Instruction {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private Integer step;

  private String instruction;

  @ManyToOne
  @JoinColumn(name = "recipe_id")
  @JsonIgnore
  private Recipe recipe;

  public Instruction(Object o, int i, String first_instruction_description) {}

  public Instruction(Object o) {
  }
}
