package pdi.project.com.example.recipeapi.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Ingredient {

    private Long id;
    private String name;
    private IngredientType type;
    private UnitOfMensurement measure;
    private String quantity;
}
