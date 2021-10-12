package com.revature.services;

import com.revature.repository.FurnitureDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("furnitureService")
public class FurnitureService {
    private FurnitureDao furnitureDao;

    @Autowired
    public FurnitureService(FurnitureDao furnitureDao){
        this.furnitureDao = furnitureDao;
    }
}
