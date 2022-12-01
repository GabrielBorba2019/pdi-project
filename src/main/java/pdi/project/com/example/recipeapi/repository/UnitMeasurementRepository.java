package pdi.project.com.example.recipeapi.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import pdi.project.com.example.recipeapi.domain.UnitMeasurement;

public interface UnitMeasurementRepository extends JpaRepository<UnitMeasurement, Long> {
}
