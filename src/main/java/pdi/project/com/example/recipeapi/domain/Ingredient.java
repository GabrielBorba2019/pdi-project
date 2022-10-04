package pdi.project.com.example.recipeapi.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tb_ingredient")
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String quantity;

    @JsonIgnore
    @ManyToMany(mappedBy = "ingredients")
    private Set<Recipe> recipes = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "unit_id")
    private UnitMeasurement unitMeasurement;

    public Ingredient() {

    }

    public Ingredient(Long id, String name, String quantity, UnitMeasurement unitMeasurement) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.unitMeasurement = unitMeasurement;
    }

    public Long getId() {
        return id;
    }

    public Set<Recipe> getRecipes() {
        return recipes;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UnitMeasurement getUnitMeasurement() {
        return unitMeasurement;
    }

    public void setUnitMeasurement(UnitMeasurement unitMeasurement) {
        this.unitMeasurement = unitMeasurement;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}
