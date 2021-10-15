/*
Andrew Patrick 10/12/2021
 */

package com.revature.repository;

import com.revature.models.Furniture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface FurnitureDao extends JpaRepository<Furniture, Integer> {
}
