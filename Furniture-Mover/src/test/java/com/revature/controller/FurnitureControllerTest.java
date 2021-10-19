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
    void deleteFurniture() {
    }

    @Test
    void getAllFurnitureForAHome() {
    }
}