package pdi.project.com.example.recipeapi.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pdi.project.com.example.recipeapi.domain.Recipe;
import pdi.project.com.example.recipeapi.dto.RecipeDTO;
import pdi.project.com.example.recipeapi.service.RecipeService;

@RequestMapping("recipe")
@RestController
public class RecipeController {

    private final RecipeService service;

    @Autowired
    public RecipeController(RecipeService service) {
        this.service = service;
    }

    @GetMapping("/ping")
    public String ping() {
        return "pong";

    }

    @PostMapping("/add/recipe")
    public ResponseEntity<?> creataRecipe(@RequestBody RecipeDTO recipeDTO) {
        Recipe recipe = service.addRecipe(recipeDTO);

        return new ResponseEntity<>(recipe, HttpStatus.CREATED);
    }


}
