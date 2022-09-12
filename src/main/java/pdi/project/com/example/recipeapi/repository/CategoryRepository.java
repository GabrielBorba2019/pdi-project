package pdi.project.com.example.recipeapi.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import pdi.project.com.example.recipeapi.domain.Category;

@Component
@Scope("singleton")
public class CategoryRepository {
  private final List<Category> categories;

  private static final Category desert = new Category(1L, "Desert");
  private static final Category meat = new Category(2L, "Meat");

  public CategoryRepository(List<Category> categories) {
    this.categories = categories;

    categories.add(desert);
    categories.add(meat);
  }

  public Optional<Category> findById(Long id) {
    return categories.stream().filter(category -> category.getId().equals(id)).findFirst();
  }
}
