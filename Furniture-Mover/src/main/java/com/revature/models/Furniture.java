/*
Andrew Patrick 10/12/2021
 */

package com.revature.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Represents a piece of furniture.  Each piece of furniture must have a name, a size in square feet, and must
 * be associated with a home.  Each furniture entry in the database will have a foreign key associated with the owner
 * home.
 */

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "furniture")
public class Furniture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "furniture_id")
    private Integer id;

    @Column(name = "furniture_name", nullable = false)
    private String name;

    @Column(name = "furniture_size_sqr_ft", nullable = false)
    private Integer sizeSqrFt;

    @ManyToOne(cascade = CascadeType.ALL)
    private Home ownerHome;
}
