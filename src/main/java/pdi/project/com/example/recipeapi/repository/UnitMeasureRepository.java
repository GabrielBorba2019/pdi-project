package pdi.project.com.example.recipeapi.repository;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import pdi.project.com.example.recipeapi.domain.UnitOfMensurement;

import java.util.List;
import java.util.Optional;

@Component
@Scope("singleton")
public class UnitMeasureRepository {

    private final List<UnitOfMensurement> units;

    private final UnitOfMensurement kilo = new UnitOfMensurement(1L, "kg");
    private final UnitOfMensurement unidade = new UnitOfMensurement(2L, "unidade");
    private final UnitOfMensurement colherDeSopa = new UnitOfMensurement(3L, "colher de sopa");


    public UnitMeasureRepository(List<UnitOfMensurement> units) {
        this.units = units;

        units.add(kilo);
        units.add(unidade);
        units.add(colherDeSopa);
    }

    public UnitOfMensurement findById(Long id){
        return units.stream().filter(unit -> unit.getId() == id).findFirst().orElse(null);
    }
}
