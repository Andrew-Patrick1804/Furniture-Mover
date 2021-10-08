package com.revature.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "homes")
public class Home {

    private Integer id;

    private String name;

    private Integer sizeSqrFt;

    private Integer occupiedSqrFt;

    private List<Furniture> furniture;
}
