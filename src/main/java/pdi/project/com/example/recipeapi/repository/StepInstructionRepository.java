package pdi.project.com.example.recipeapi.repository;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import pdi.project.com.example.recipeapi.domain.StepInstruction;
import pdi.project.com.example.recipeapi.dto.RecipeDTO;

import java.util.ArrayList;
import java.util.List;

@Component
@Scope("singleton")
public class StepInstructionRepository {
    private static final List<StepInstruction> instructions = new ArrayList<>();


//    //public StepInstructionRepository() {
//        instructions = new ArrayList<>();
//    }

    public StepInstruction addStepInstruction(StepInstruction instruction) {
        long id = instructions.size() + 1;

        instruction.setId(id);
        instructions.add(instruction);

        return instruction;
    }

}

