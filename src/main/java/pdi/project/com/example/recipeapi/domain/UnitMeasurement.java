package pdi.project.com.example.recipeapi.domain;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "tb_unit")
public class UnitMeasurement {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String unit;


  public UnitMeasurement(){

  }

  public UnitMeasurement(Long id, String unit) {
    this.id = id;
    this.unit = unit;
  }



}
