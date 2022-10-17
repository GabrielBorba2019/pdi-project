package pdi.project.com.example.recipeapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pdi.project.com.example.recipeapi.domain.Instruction;

@Repository
public interface InstructionRepository extends JpaRepository<Instruction, Long> {
}
