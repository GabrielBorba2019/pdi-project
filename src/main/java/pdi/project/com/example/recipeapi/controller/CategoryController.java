package pdi.project.com.example.recipeapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pdi.project.com.example.recipeapi.domain.Category;
import pdi.project.com.example.recipeapi.domain.Instruction;
import pdi.project.com.example.recipeapi.service.CategoryServiceV2;
import pdi.project.com.example.recipeapi.service.InstructionServiceV2;

import java.util.List;

@RequestMapping(value = "categories")
@RestController
public class CategoryController {

  private CategoryServiceV2 serviceV2;

  @Autowired
  public CategoryController(CategoryServiceV2 serviceV2) {
    this.serviceV2 = serviceV2;
  }


  @GetMapping("/all")
  public ResponseEntity<List<Category>> findAll(){
    List<Category> list = serviceV2.findAll();
    return ResponseEntity.ok().body(list);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Category> findById(@PathVariable Long id){
    return ResponseEntity.ok().body(serviceV2.findById(id));
  }


}
