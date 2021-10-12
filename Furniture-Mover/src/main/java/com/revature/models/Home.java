/*
Andrew Patrick 10/12/2021
 */

package com.revature.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

/**
 * Represents a home.  Each home must have a name and a size in square feet.
 * Each home has 0 or more pieces of furniture that take up space in the home.
 * A home cannot have more than half of its size occupied with furniture.
 * When deleting a home, all the furniture in the home will be deleted with it.
 */

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "homes")
public class Home {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "home_id")
    private Integer id;

    @Column(name = "home_name", nullable = false)
    private String name;

    @Column(name = "home_size_sqr_ft", nullable = false)
    private Integer sizeSqrFt;

    @Column(name = "home_occupied_sqr_ft")
    private Integer occupiedSqrFt = 0;

    @OneToMany(mappedBy = "ownerHome", cascade = CascadeType.ALL)
    private List<Furniture> furniture;
}
