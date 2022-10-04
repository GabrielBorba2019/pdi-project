package pdi.project.com.example.recipeapi.repository;

import java.util.ArrayList;
import java.util.List;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import pdi.project.com.example.recipeapi.domain.Ingredient;

@Component
@Repository
@Scope("singleton")
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {

}
