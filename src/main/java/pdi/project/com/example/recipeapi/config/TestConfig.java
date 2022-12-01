package pdi.project.com.example.recipeapi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import pdi.project.com.example.recipeapi.domain.*;
import pdi.project.com.example.recipeapi.repository.*;


import java.time.Instant;
import java.util.Arrays;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private InstructionRepository instructionRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private SubCategoryRepository subCategoryRepository;

    @Autowired
    private IngredientRepository ingredientRepository;

    @Autowired
    private UnitMeasurementRepository unitMeasurementRepository;

    @Override
    public void run(String... args) throws Exception {

        //Data seeding de Category
        Category c1 = new Category(null, "Bolos e Tortas");
        Category c2 = new Category(null ,"Carnes");
        Category c3 = new Category(null ,"Aves");
        Category c4 = new Category(null ,"Massas");

        //Data seeding de SubCategory
        SubCategory s1 = new SubCategory(null, "Bolos Comum",c1);
        SubCategory s2 = new SubCategory(null ,"Tortas de Liquidificador",c1);
        SubCategory s3 = new SubCategory(null ,"Recheados & Cobertura",c1);
        SubCategory s4 = new SubCategory(null ,"Carneiro",c2);
        SubCategory s5 = new SubCategory(null ,"Bovina",c2);
        SubCategory s6 = new SubCategory(null ,"Porco",c2);
        SubCategory s7 = new SubCategory(null ,"Frango",c3);
        SubCategory s8 = new SubCategory(null ,"Peru",c3);
        SubCategory s9 = new SubCategory(null ,"Pato",c3);
        SubCategory s10 = new SubCategory(null ,"Pães",c4);
        SubCategory s11 = new SubCategory(null ,"Pizza",c4);
        SubCategory s12 = new SubCategory(null ,"Lasanha",c4);


        //Data seeding de UnitMeasurement
        UnitMeasurement u1 = new UnitMeasurement(null, "Unidade");
        UnitMeasurement u2 = new UnitMeasurement(null, "Xícara");
        UnitMeasurement u3 = new UnitMeasurement(null, "Colher de Sopa");
        UnitMeasurement u4 = new UnitMeasurement(null, "Copo");


        //Data seeding de Ingredient
        Ingredient i1 = new Ingredient(null , "Ovos","01", u1);
        Ingredient i2 = new Ingredient(null , "Açucar","02", u2);
        Ingredient i3 = new Ingredient(null , "Fubá","02" ,u2);
        Ingredient i4 = new Ingredient(null , "Farinha de Trigo","3", u3);
        Ingredient i5 = new Ingredient(null , "Óleo", "1/2",u4);
        Ingredient i6 = new Ingredient(null , "Leite", "1",u4);
        Ingredient i7 = new Ingredient(null , "Fermento em pó", "1",u3);


        //Data seeding de Recipe
        Recipe r1 = new Recipe(null, "Bolo de Fubá", c1 , s1, null, 40, Instant.parse("2022-09-29T12:00:00Z"));
        Recipe r2 = new Recipe(null, "Bolo de Cenoura", c1 , s1,  null, 8, Instant.parse("2022-08-29T12:00:00Z"));


        //Data seeding de Instruções
        Instruction inst1 = new Instruction(null,1, "Em uma batedeira, bata as claras em neve e acrescente o açúcar.", r1);
        Instruction inst2 = new Instruction(null,2, "Adicione as gemas, a margarina, o leite, a farinha de trigo, o fubá e continue batendo.", r1);
        Instruction inst3 = new Instruction(null,3, "Acrescente por último o fermento e misture com uma colher ou espátula.", r1);
        Instruction inst4 = new Instruction(null,4, "Despeje a massa em uma forma untada e deixe assar em forno médio (180° C), preaquecido, por aproximadamente 30 minutos.", r1);

        Instruction inst5 = new Instruction(null,1, "Em um liquidificador, adicione a cenoura, os ovos e o óleo, depois misture.", r2);
        Instruction inst6 = new Instruction(null,2, "Acrescente o açúcar e bata novamente por 5 minutos.", r2);
        Instruction inst7 = new Instruction(null,3, "Em uma tigela ou na batedeira, adicione a farinha de trigo e depois misture novamente.", r2);
        Instruction inst8 = new Instruction(null,4, "Acrescente o fermento e misture lentamente com uma colher.", r2);
        Instruction inst9 = new Instruction(null,5, "Asse em um forno preaquecido a 180° C por aproximadamente 40 minutos.", r2);

        categoryRepository.saveAll(Arrays.asList(c1,c2,c3,c4));
        subCategoryRepository.saveAll(Arrays.asList(s1,s2,s3,s4,s5,s6,s7,s8,s9,s10,s11,s12));
        unitMeasurementRepository.saveAll(Arrays.asList(u1,u2,u3,u4));
        ingredientRepository.saveAll(Arrays.asList(i1,i2,i3,i4,i5,i6,i7));
        instructionRepository.saveAll(Arrays.asList(inst1,inst2,inst3,inst4,inst5,inst6,inst7,inst8,inst9));
        recipeRepository.saveAll(Arrays.asList(r1,r2));



        r1.getIngredients().add(i1);
        r1.getIngredients().add(i2);
        r1.getIngredients().add(i3);
        r1.getIngredients().add(i4);
        r1.getIngredients().add(i5);
        r1.getIngredients().add(i6);
        r1.getIngredients().add(i7);


        recipeRepository.save(r1);

    }
}
