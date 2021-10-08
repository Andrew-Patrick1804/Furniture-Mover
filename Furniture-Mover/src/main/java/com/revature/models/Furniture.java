package com.revature.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "furniture")
public class Furniture {

    private Integer id;

    private Integer homeId;

    private String name;

    private Integer sizeSqrFt;
}
