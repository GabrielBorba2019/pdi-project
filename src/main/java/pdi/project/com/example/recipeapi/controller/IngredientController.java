package pdi.project.com.example.recipeapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pdi.project.com.example.recipeapi.domain.Ingredient;
import pdi.project.com.example.recipeapi.service.IngredientServiceV2;

import java.util.List;

@RequestMapping(value = "ingredients")
@RestController
public class IngredientController {

  private IngredientServiceV2 serviceV2;

  @Autowired
  public IngredientController(IngredientServiceV2 serviceV2) {
    this.serviceV2 = serviceV2;
  }

  @GetMapping("/all")
  public ResponseEntity<List<Ingredient>> findAll(){
    List<Ingredient> list = serviceV2.findAll();
    return ResponseEntity.ok().body(list);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Ingredient> findById(@PathVariable Long id){
    return ResponseEntity.ok().body(serviceV2.findById(id));
  }
}
