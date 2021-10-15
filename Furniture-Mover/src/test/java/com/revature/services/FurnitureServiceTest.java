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
}