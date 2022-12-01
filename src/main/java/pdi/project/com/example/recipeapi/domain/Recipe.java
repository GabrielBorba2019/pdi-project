package pdi.project.com.example.recipeapi.domain;

import java.time.Instant;
import java.time.LocalTime;
import java.util.*;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Entity
@Table(name = "tb_recipe")
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "subCategory_id")
    private SubCategory subCategory;

    private LocalTime prepareTime;

    private Integer yield;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T':mm:ss'Z'", timezone = "GMT")
    private Instant moment;

    @OneToMany(mappedBy = "recipe")
    private List<Instruction> Instructions = new ArrayList<>();


    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "tb_recipe_ingredient",
            joinColumns = @JoinColumn(name = "recipe_id"),
            inverseJoinColumns = @JoinColumn(name = "ingredient_id"))
    private Set<Ingredient> ingredients = new HashSet<>();

    public Recipe(Long id, String name, Category category, SubCategory subCategory, LocalTime prepareTime, Integer yield, Instant moment) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.subCategory = subCategory;
        this.prepareTime = prepareTime;
        this.yield = yield;
        this.moment = moment;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setSubCategory(SubCategory subCategory) {
        this.subCategory = subCategory;
    }

    public void setPrepareTime(LocalTime prepareTime) {
        this.prepareTime = prepareTime;
    }

    public void setYield(Integer yield) {
        this.yield = yield;
    }

    public void setMoment(Instant moment) {
        this.moment = moment;
    }

    public String getName() {
        return name;
    }

    public Category getCategory() {
        return category;
    }

    public SubCategory getSubCategory() {
        return subCategory;
    }

    public Set<Ingredient> getIngredients() {
        return ingredients;
    }

    public LocalTime getPrepareTime() {
        return prepareTime;
    }

    public Integer getYield() {
        return yield;
    }

    public Instant getMoment() {
        return moment;
    }

    public List<Instruction> getInstructions() {
        return Instructions;
    }


}
