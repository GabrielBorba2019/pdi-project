package pdi.project.com.example.recipeapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pdi.project.com.example.recipeapi.domain.SubCategory;
import pdi.project.com.example.recipeapi.service.SubCategoryServiceV2;

import java.util.List;

@RequestMapping(value = "subcategories")
@RestController
public class SubCategoryController {

  private SubCategoryServiceV2 serviceV2;

  @Autowired
  public SubCategoryController(SubCategoryServiceV2 serviceV2) {
    this.serviceV2 = serviceV2;
  }

  @GetMapping("/all")
  public ResponseEntity<List<SubCategory>> findAll(){
    List<SubCategory> list = serviceV2.findAll();
    return ResponseEntity.ok().body(list);
  }

  @GetMapping("/{id}")
  public ResponseEntity<SubCategory> findById(@PathVariable Long id){
    return ResponseEntity.ok().body(serviceV2.findById(id));
  }


}
