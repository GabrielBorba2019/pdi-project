package pdi.project.com.example.recipeapi.repository;

import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pdi.project.com.example.recipeapi.domain.IngredientType;


@Scope("singleton")
@Repository
public interface IngredientTypeRepository extends JpaRepository<IngredientType, Long>{
}