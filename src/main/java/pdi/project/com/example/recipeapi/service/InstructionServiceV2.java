package pdi.project.com.example.recipeapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pdi.project.com.example.recipeapi.domain.Instruction;
import pdi.project.com.example.recipeapi.domain.Recipe;
import pdi.project.com.example.recipeapi.repository.InstructionRepository;
import pdi.project.com.example.recipeapi.repository.RecipeRepository;

import java.util.List;

@Service
public class InstructionServiceV2 {

    @Autowired
    private InstructionRepository instructionRepository;

    public List<Instruction> findAll(){
        return instructionRepository.findAll();
    }

    public Instruction findById(Long id){
        return instructionRepository.findById(id).get();
    }
}
