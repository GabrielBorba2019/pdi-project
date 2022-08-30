package pdi.project.com.example.recipeapi.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pdi.project.com.example.recipeapi.domain.StepInstruction;
import pdi.project.com.example.recipeapi.dto.StepInstructionDTO;
import pdi.project.com.example.recipeapi.exception.StepInstructionValidationException;
import pdi.project.com.example.recipeapi.repository.StepInstructionRepository;

import static java.util.Objects.isNull;

@Service
public class StepInstructionService {

    private final StepInstructionRepository stepInstructionRepository;

    @Autowired
    public StepInstructionService(StepInstructionRepository stepInstructionRepository) {

        this.stepInstructionRepository = stepInstructionRepository;
    }

    public List<StepInstruction> createInstructions(List<StepInstructionDTO> stepInstructionDTOS) {
        if (isNull(stepInstructionDTOS) || stepInstructionDTOS.isEmpty()) {
            throw new StepInstructionValidationException("Instruction list cannot be empty or null");
        }

        List<StepInstruction> instructions =
                stepInstructionDTOS.stream().map(this::saveInstruction).collect(Collectors.toList());

        int counter = 1;
        for (StepInstruction instruction : instructions) instruction.setStep(counter++);

        return instructions;
    }

    private StepInstruction saveInstruction(StepInstructionDTO stepInstructionDTO) {
        var stepInstruction = new StepInstruction(1l, 1, stepInstructionDTO.getInstruction());

        if (isNull(stepInstructionDTO.getInstruction()) || stepInstructionDTO.getInstruction().isEmpty()) {
            throw new StepInstructionValidationException("Instruction cannot be empty or null");
        }

        return stepInstructionRepository.addStepInstruction(stepInstruction);
    }
}
