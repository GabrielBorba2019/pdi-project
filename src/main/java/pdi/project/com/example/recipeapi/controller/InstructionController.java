package pdi.project.com.example.recipeapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pdi.project.com.example.recipeapi.domain.Instruction;
import pdi.project.com.example.recipeapi.domain.Recipe;
import pdi.project.com.example.recipeapi.service.InstructionServiceV2;

import java.util.List;

@RequestMapping(value = "instructions")
@RestController
public class InstructionController {

  private InstructionServiceV2 serviceV2;

  @Autowired
  public InstructionController(InstructionServiceV2 serviceV2) {
    this.serviceV2 = serviceV2;
  }

  @GetMapping("/all")
  public ResponseEntity<List<Instruction>> findAll(){
    List<Instruction> list = serviceV2.findAll();
    return ResponseEntity.ok().body(list);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Instruction> findById(@PathVariable Long id){
    return ResponseEntity.ok().body(serviceV2.findById(id));
  }


}
