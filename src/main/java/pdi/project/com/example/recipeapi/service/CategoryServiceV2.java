package pdi.project.com.example.recipeapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pdi.project.com.example.recipeapi.domain.Category;
import pdi.project.com.example.recipeapi.repository.CategoryRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceV2 {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> findAll(){
        return categoryRepository.findAll();
    }

    public Category findById(Long id){
        Optional<Category> cat = categoryRepository.findById(id);
        return cat.get();
    }
}
