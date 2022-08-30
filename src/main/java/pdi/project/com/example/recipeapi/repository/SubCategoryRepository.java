package pdi.project.com.example.recipeapi.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import pdi.project.com.example.recipeapi.domain.SubCategory;

@Component
@Scope("singleton")
public class SubCategoryRepository {
  private final List<SubCategory> subCategories;

  private static final SubCategory carneSuina = new SubCategory(1L, "Pork");
  private static final SubCategory carneBovina = new SubCategory(2L, "Bovinus");
  private static final SubCategory chocolate = new SubCategory(3L, "Chocolate");

  public SubCategoryRepository(List<SubCategory> subCategories) {
    this.subCategories = subCategories;

    subCategories.add(carneSuina);
    subCategories.add(carneBovina);
    subCategories.add(chocolate);
  }

  public Optional<SubCategory> findById(Long id) {
    return subCategories.stream().filter(subCategory -> subCategory.getId() == id).findFirst();
  }
}
