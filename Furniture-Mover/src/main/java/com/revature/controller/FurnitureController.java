package com.revature.controller;

import com.revature.models.Furniture;
import com.revature.models.Response;
import com.revature.services.FurnitureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("furniture")
public class FurnitureController {

    private FurnitureService furnitureService;

    @Autowired
    public FurnitureController(FurnitureService furnitureService){
        this.furnitureService = furnitureService;
    }

    @PostMapping
    public Response createFurniture(@RequestBody Furniture furniture){
        Furniture newFurniture = this.furnitureService.addFurniture(furniture);
        return new Response(true, "New Furniture Created", newFurniture);
    }

    @DeleteMapping("{furnitureId}")
    public Response deleteFurniture(@PathVariable Integer furnitureId){
        Furniture furniture = this.furnitureService.deleteFurniture(furnitureId);
        if (furniture == null){
            return new Response(false, "Furniture Not Deleted Because It Does Not Exist", null);
        }
        else{
            return new Response(true, "Furniture With Given ID Deleted", furniture);
        }
    }

    @GetMapping("{homeId}")
    public Response getAllFurnitureForAHome(@PathVariable Integer homeId){
        List<Furniture> allFurniture = this.furnitureService.getAllFurnitureForAHome(homeId);
        return new Response(true, "Given Home's Furniture", allFurniture);
    }
}
