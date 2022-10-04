package pdi.project.com.example.recipeapi.service;

import static java.util.Objects.isNull;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pdi.project.com.example.recipeapi.domain.Instruction;
import pdi.project.com.example.recipeapi.dto.StepInstructionDTO;
import pdi.project.com.example.recipeapi.exception.StepInstructionValidationException;
import pdi.project.com.example.recipeapi.repository.InstructionRepository;

@Service
public class StepInstructionService {

  private final InstructionRepository instructionRepository;

  @Autowired
  public StepInstructionService(InstructionRepository instructionRepository) {

    this.instructionRepository = instructionRepository;
  }

  public List<Instruction> createInstructions(List<StepInstructionDTO> stepInstructionDTOS) {
    if (isNull(stepInstructionDTOS) || stepInstructionDTOS.isEmpty()) {
      throw new StepInstructionValidationException("Instruction list cannot be empty or null");
    }

    List<Instruction> instructions =
        stepInstructionDTOS.stream().map(this::saveInstruction).collect(Collectors.toList());

    int counter = 1;
    for (Instruction instruction : instructions) instruction.setStep(counter++);

    return instructions;
  }

  private Instruction saveInstruction(StepInstructionDTO stepInstructionDTO) {
    var stepInstruction = new Instruction(1l, 1, stepInstructionDTO.getInstruction());

    if (isNull(stepInstructionDTO.getInstruction())
        || stepInstructionDTO.getInstruction().isEmpty()) {
      throw new StepInstructionValidationException("Instruction cannot be empty or null");
    }

    return instructionRepository.save(stepInstruction);
  }
}
