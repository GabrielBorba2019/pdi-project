package pdi.project.com.example.recipeapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pdi.project.com.example.recipeapi.domain.Recipe;
import pdi.project.com.example.recipeapi.domain.UnitMeasurement;
import pdi.project.com.example.recipeapi.repository.RecipeRepository;
import pdi.project.com.example.recipeapi.repository.UnitMeasurementRepository;

import java.util.List;

@Service
public class UnitMeasurementServiceV2 {

    @Autowired
    private UnitMeasurementRepository unitMeasurementRepository;

    public List<UnitMeasurement> findAll(){
        return unitMeasurementRepository.findAll();
    }

    public UnitMeasurement findById(Long id){
        return unitMeasurementRepository.findById(id).get();
    }
}
