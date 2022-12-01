package pdi.project.com.example.recipeapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pdi.project.com.example.recipeapi.domain.SubCategory;
import pdi.project.com.example.recipeapi.repository.SubCategoryRepository;

import java.util.List;
import java.util.Optional;

@Service
public class SubCategoryServiceV2 {

    @Autowired
    private SubCategoryRepository subCategoryRepository;

    public List<SubCategory> findAll() {
        return subCategoryRepository.findAll();
    }

    public SubCategory findById(Long id) {
        Optional<SubCategory> subCat = subCategoryRepository.findById(id);
        return subCat.get();
    }
}
