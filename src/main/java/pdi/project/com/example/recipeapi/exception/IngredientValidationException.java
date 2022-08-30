package pdi.project.com.example.recipeapi.exception;

public class IngredientValidationException extends RuntimeException {
    public IngredientValidationException(String message) {
        super(message);
    }
}
