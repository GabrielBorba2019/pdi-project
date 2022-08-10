package pdi.project.com.example.recipeapi.dto;

import lombok.*;
import pdi.project.com.example.recipeapi.domain.IngredientType;
import pdi.project.com.example.recipeapi.domain.UnitOfMensurement;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IngredientDTO {

    private String name;
    private long type;
    private long unit;
    private String quantity;
}
