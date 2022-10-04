package pdi.project.com.example.recipeapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pdi.project.com.example.recipeapi.domain.Recipe;
import pdi.project.com.example.recipeapi.dto.RecipeDTO;
import pdi.project.com.example.recipeapi.service.RecipeServiceV2;

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


}
