package pdi.project.com.example.recipeapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pdi.project.com.example.recipeapi.domain.SubCategory;
import pdi.project.com.example.recipeapi.repository.SubCategoryRepository;

import java.util.List;

@Service
public class SubCategoryServiceV2 {

    @Autowired
    private SubCategoryRepository subCategoryRepository;

    public List<SubCategory> findAll(){
        return subCategoryRepository.findAll();
    }

    public SubCategory findById(Long id){
        return subCategoryRepository.findById(id).get();
    }
}
