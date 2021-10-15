package com.revature.services;

import com.revature.models.Furniture;
import com.revature.repository.FurnitureDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("furnitureService")
public class FurnitureService {
    private FurnitureDao furnitureDao;

    @Autowired
    public FurnitureService(FurnitureDao furnitureDao){
        this.furnitureDao = furnitureDao;
    }

    public Furniture addFurniture(Furniture furniture){
        return this.furnitureDao.save(furniture);
    }

    public Furniture deleteFurniture(Integer furnitureId){
        /* check that the furniture with the given ID exists
        *  if not, return null, otherwise delete it. */
        Furniture checkFurniture = this.furnitureDao.findById(furnitureId).orElse(null);
        if(checkFurniture != null){
            this.furnitureDao.deleteById(furnitureId);
            return checkFurniture;
        }
        return null;
    }
}
