package com.revature.services;

import com.revature.models.Furniture;
import com.revature.models.Home;
import com.revature.repository.FurnitureDao;
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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class FurnitureServiceTest {

    FurnitureService furnitureService;

    @Mock
    FurnitureDao furnitureDao;

    @BeforeEach
    void setUp() {
        this.furnitureService = new FurnitureService(furnitureDao);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void addFurniture() {

        //ASSIGN
        Home home = new Home();
        home.setId(1);

        Furniture expectedResult = new Furniture();
        expectedResult.setId(1);
        expectedResult.setName("Table");
        expectedResult.setSizeSqrFt(18);
        expectedResult.setOwnerHome(home);

        Mockito.when(this.furnitureDao.save(expectedResult)).thenReturn(expectedResult);

        //ACT
        Furniture actualResult = this.furnitureService.addFurniture(expectedResult);

        //ASSERT
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void deleteFurnitureSuccessful() {

        //ASSIGN
        Integer furnitureId = 1;

        Home home = new Home();
        home.setId(1);

        Furniture expectedResult = new Furniture();
        expectedResult.setId(furnitureId);
        expectedResult.setName("Table");
        expectedResult.setSizeSqrFt(18);
        expectedResult.setOwnerHome(home);

        Mockito.when(this.furnitureDao.findById(furnitureId)).thenReturn(Optional.of(expectedResult));

        //ACT
        Furniture actualResult = this.furnitureService.deleteFurniture(furnitureId);

        //ASSERT
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void deleteFurnitureNotSuccessful() {

        //ASSIGN
        Integer furnitureId = 1;

        Furniture expectedResult = null;

        Mockito.when(this.furnitureDao.findById(furnitureId)).thenReturn(Optional.empty());

        //ACT
        Furniture actualResult = this.furnitureService.deleteFurniture(furnitureId);

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

        Furniture furniture1 = new Furniture();
        furniture1.setId(1);
        furniture1.setName("Table");
        furniture1.setSizeSqrFt(10);
        furniture1.setOwnerHome(home);

        Furniture furniture2 = new Furniture();
        furniture2.setId(1);
        furniture2.setName("Table");
        furniture2.setSizeSqrFt(10);
        furniture2.setOwnerHome(home);

        Furniture furniture3 = new Furniture();
        furniture3.setId(1);
        furniture3.setName("Table");
        furniture3.setSizeSqrFt(10);
        furniture3.setOwnerHome(home);

        List<Furniture> expectedResult = new ArrayList<>();
        expectedResult.add(furniture1);
        expectedResult.add(furniture2);
        expectedResult.add(furniture3);

        Mockito.when(this.furnitureDao.getFurnitureByOwnerHomeId(homeId)).thenReturn(expectedResult);

        //ACT
        List<Furniture> actualResult = this.furnitureService.getAllFurnitureForAHome(homeId);

        //ASSERT
        assertEquals(expectedResult, actualResult);
    }
}