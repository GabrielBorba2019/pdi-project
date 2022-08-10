package pdi.project.com.example.recipeapi.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Optional;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Ingredient {

    private String name;
    private IngredientType type;
    private UnitOfMensurement measure;
    private String quantity;
}
