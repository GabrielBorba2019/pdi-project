package pdi.project.com.example.recipeapi.exception;

public class RecipeValidationException extends RuntimeException {
  public RecipeValidationException(String message) {
    super(message);
  }
}
