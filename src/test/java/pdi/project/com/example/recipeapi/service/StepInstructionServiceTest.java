package pdi.project.com.example.recipeapi.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.mockito.Mockito;
import pdi.project.com.example.recipeapi.domain.StepInstruction;
import pdi.project.com.example.recipeapi.dto.StepInstructionDTO;
import pdi.project.com.example.recipeapi.exception.StepInstructionValidationException;
import pdi.project.com.example.recipeapi.repository.StepInstructionRepository;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class StepInstructionServiceTest {

    private StepInstructionRepository stepInstructionRepository = Mockito.mock(StepInstructionRepository.class);


    private StepInstructionService service = new StepInstructionService(stepInstructionRepository);

    private final StepInstruction FIRST_STEP = new StepInstruction(0l, 1, "First instruction description");
    private final StepInstruction EMPTY_STEP = new StepInstruction(2l, 3, "");
    private final StepInstruction NULL_STEP = new StepInstruction(null);

    @Test
    @DisplayName("Create instructions with empty instructions list")
    void createStepInstrucutionsWithEmptyList() {
        RuntimeException exception = assertThrows(StepInstructionValidationException.class, () -> {
            service.createInstructions(Collections.emptyList());
        });

        String errorMessage = exception.getMessage();

        assertEquals("Instruction list cannot be empty or null", errorMessage);

    }

    @Test
    @DisplayName("Create instructions with empty instructions list")
    void createStepInstrucutionsWithNullList() {
        RuntimeException exception = assertThrows(StepInstructionValidationException.class, () -> {
            service.createInstructions(null);
        });

        String errorMessage = exception.getMessage();

        assertEquals("Instruction list cannot be empty or null", errorMessage);

    }

    @Test
    @DisplayName("Create step instruction without any information")
    void createInstructionsWithoutInformation() {
       //Given
        var instructionDTO = mockInstructionDTO(EMPTY_STEP);
        var instruction = new StepInstruction(1l, 1, instructionDTO.getInstruction());

        //When
        when(stepInstructionRepository.addStepInstruction(instruction)).thenReturn(instruction);

        //Then
        RuntimeException exception = assertThrows(StepInstructionValidationException.class, () -> {
           service.createInstructions(List.of(instructionDTO));
        });

        String errorMessage = exception.getMessage();

        assertEquals("Instruction cannot be empty or null",  errorMessage);

    }

    @Test
    @DisplayName("Create step instruction null information")
    void createInstructionsWithNullInformation() {
        //Given
        var instructionDTO = mockInstructionDTO(NULL_STEP);
        var instruction = new StepInstruction(1l, 1, instructionDTO.getInstruction());

        //When
        when(stepInstructionRepository.addStepInstruction(instruction)).thenReturn(instruction);

        //Then
        RuntimeException exception = assertThrows(StepInstructionValidationException.class, () -> {
            service.createInstructions(List.of(instructionDTO));
        });

        String errorMessage = exception.getMessage();

        assertEquals("Instruction cannot be empty or null",  errorMessage);

    }

    @Test
    @DisplayName("create step instruction")
    void createStepInstruction() {
        //Given
        var instructionDTO = mockInstructionDTO(FIRST_STEP);
        var instruction = new StepInstruction(1l, 1, instructionDTO.getInstruction());

        //When
        when(stepInstructionRepository.addStepInstruction(any())).thenReturn(instruction);

        //Then
       var stepInstruction = service.createInstructions(List.of(instructionDTO));
       assertEquals(1, stepInstruction.size());
    }

    private StepInstructionDTO mockInstructionDTO(StepInstruction stepInstruction) {
        var instructionDTO = new StepInstructionDTO(stepInstruction.getInstruction());

        return instructionDTO;
    }
}