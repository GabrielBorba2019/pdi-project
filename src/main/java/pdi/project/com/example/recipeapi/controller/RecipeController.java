package pdi.project.com.example.recipeapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pdi.project.com.example.recipeapi.domain.Recipe;
import pdi.project.com.example.recipeapi.dto.RecipeDTO;
import pdi.project.com.example.recipeapi.service.RecipeServiceV2;

import java.net.URI;
import java.util.List;

@RequestMapping(value = "recipes")
@RestController
public class RecipeController {


  private final RecipeServiceV2 serviceV2;

  @Autowired
  public RecipeController(RecipeServiceV2 serviceV2) {

    this.serviceV2 = serviceV2;
  }

  @GetMapping("/all")
  public ResponseEntity<List<Recipe>> findAll(){
    List<Recipe> list = serviceV2.findAll();
    return ResponseEntity.ok().body(list);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Recipe> findById(@PathVariable Long id){
    return ResponseEntity.ok().body(serviceV2.findById(id));
  }

  @PostMapping("/insert")
  public ResponseEntity<Recipe> createRecipe(@RequestBody RecipeDTO recipeDto){
    Recipe recipe = serviceV2.insert(recipeDto);
    URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(recipe.getId()).toUri();
    return ResponseEntity.created(uri).body(recipe);
  }

}
