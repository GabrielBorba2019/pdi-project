package pdi.project.com.example.recipeapi.repository;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import pdi.project.com.example.recipeapi.domain.IngredientType;

@Component
@Scope("singleton")
public class IngredientTypeRepository {

  private final List<IngredientType> types;

  private static final IngredientType proteina = new IngredientType(1l, "Proteina");
  private static final IngredientType vinagre = new IngredientType(2l, "Vinagre");
  private static final IngredientType folhas = new IngredientType(3l, "Folhas");
  private static final IngredientType legume = new IngredientType(4l, "Legume");

  public IngredientTypeRepository(List<IngredientType> types) {
    this.types = types;

    types.add(proteina);
    types.add(folhas);
    types.add(legume);
    types.add(vinagre);
  }

  public IngredientType findById(Long id) {
    return types.stream().filter(x -> x.getId() == id).findFirst().orElse(null);
  }

  public List<IngredientType> findAllByIds(List<Long> ids) {
    return types.stream().filter(type -> ids.contains(type.getId())).collect(Collectors.toList());
  }
}
