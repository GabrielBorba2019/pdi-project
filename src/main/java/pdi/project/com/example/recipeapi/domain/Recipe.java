package pdi.project.com.example.recipeapi.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Time;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class Recipe {

    private Integer id;
    private String name;
    private Category category;
    private SubCategory subCategory;
    private List<Ingredient> ingredientList;
    private List<Step> instructionList;
    private Time preperTime;
    private Integer yield;
    private Date date;


}
