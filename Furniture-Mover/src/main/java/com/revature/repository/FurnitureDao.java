/*
Andrew Patrick 10/12/2021
 */

package com.revature.repository;

import com.revature.models.Furniture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository("FurnitureDao")
@Transactional
public interface FurnitureDao extends JpaRepository<Furniture, Integer> {

    @Query("from Furniture where owner_home_home_id = :homeId")
    List<Furniture> getFurnitureByOwnerHomeId(Integer homeId);
}
