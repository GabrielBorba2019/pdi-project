package pdi.project.com.example.recipeapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pdi.project.com.example.recipeapi.domain.Category;


@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
