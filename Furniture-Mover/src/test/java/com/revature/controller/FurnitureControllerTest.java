package com.revature.controller;

import com.revature.models.Furniture;
import com.revature.models.Home;
import com.revature.models.Response;
import com.revature.services.FurnitureService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class FurnitureControllerTest {

    FurnitureController furnitureController;

    @Mock
    FurnitureService furnitureService;

    @BeforeEach
    void setUp() {
        this.furnitureController = new FurnitureController(furnitureService);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void createFurniture() {

        //ASSIGN
        Furniture furniture = new Furniture();
        furniture.setId(1);
        furniture.setName("Table");
        furniture.setSizeSqrFt(10);
        Home home = new Home();
        home.setId(1);
        furniture.setOwnerHome(home);

        Response expectedResult = new Response(true, "New Furniture Created", furniture);

        Mockito.when(this.furnitureService.addFurniture(furniture)).thenReturn(furniture);

        //ACT
        Response actualResult = this.furnitureController.createFurniture(furniture);

        //ASSERT
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void deleteFurnitureSuccessful() {

        //ASSIGN
        Integer furnitureId = 1;

        Furniture furniture = new Furniture();
        furniture.setId(1);
        furniture.setName("Table");
        furniture.setSizeSqrFt(10);

        Response expectedResult = new Response(true, "Furniture With Given ID Deleted", furniture);

        Mockito.when(this.furnitureService.deleteFurniture(furnitureId)).thenReturn(furniture);

        //ACT
        Response actualResult = this.furnitureController.deleteFurniture(furnitureId);

        //ASSERT
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void deleteFurnitureNotSuccessful() {

        //ASSIGN
        Integer furnitureId = 2;

        Response expectedResult = new Response(false, "Furniture Not Deleted Because It Does Not Exist", null);

        Mockito.when(this.furnitureService.deleteFurniture(furnitureId)).thenReturn(null);

        //ACT
        Response actualResult = this.furnitureController.deleteFurniture(furnitureId);

        //ASSERT
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void getAllFurnitureForAHome() {

        //ASSIGN
        Integer homeId = 1;

        Home home = new Home();
        home.setId(homeId);
        home.setName("Winterfell");
        home.setSizeSqrFt(1000);
        home.setOccupiedSqrFt(30);

        Furniture furniture1 = new Furniture();
        furniture1.setId(1);
        furniture1.setName("Table");
        furniture1.setSizeSqrFt(10);
        furniture1.setOwnerHome(home);

        Furniture furniture2 = new Furniture();
        furniture2.setId(2);
        furniture2.setName("Table");
        furniture2.setSizeSqrFt(10);
        furniture2.setOwnerHome(home);

        Furniture furniture3 = new Furniture();
        furniture3.setId(3);
        furniture3.setName("Table");
        furniture3.setSizeSqrFt(10);
        furniture3.setOwnerHome(home);

        List<Furniture> allFurniture = new ArrayList<>();
        allFurniture.add(furniture1);
        allFurniture.add(furniture2);
        allFurniture.add(furniture3);

        Response expectedResult = new Response(true, "Given Home's Furniture", allFurniture);

        Mockito.when(this.furnitureService.getAllFurnitureForAHome(homeId)).thenReturn(allFurniture);

        //ACT
        Response actualResult = this.furnitureController.getAllFurnitureForAHome(homeId);

        //ASSERT
        assertEquals(expectedResult, actualResult);
    }
}