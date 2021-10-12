package com.revature.services;

import com.revature.models.Home;
import com.revature.repository.HomeDao;
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
class HomeServiceTest {
    HomeService homeService;

    @Mock
    HomeDao homeDao;

    @BeforeEach
    void setUp() {
        this.homeService = new HomeService(homeDao);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getAllHomes() {

        //ASSIGN
        Home home1 = new Home();
        home1.setId(1);
        home1.setName("Winterfell");
        home1.setSizeSqrFt(1000);
        home1.setOccupiedSqrFt(0);

        Home home2 = new Home();
        home2.setId(2);
        home2.setName("Dragonstone");
        home2.setSizeSqrFt(1000);
        home2.setOccupiedSqrFt(0);

        List<Home> expectedResult = new ArrayList<>();
        expectedResult.add(home1);
        expectedResult.add(home2);

        Mockito.when(homeDao.findAll()).thenReturn(expectedResult);


        //ACT
        List<Home> actualResult = this.homeService.getAllHomes();

        //ASSERT
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void createHome() {

        //ASSIGN
        Home expectedResult = new Home();
        expectedResult.setId(1);
        expectedResult.setName("Winterfell");
        expectedResult.setSizeSqrFt(1000);
        expectedResult.setOccupiedSqrFt(0);

        Mockito.when(this.homeDao.save(expectedResult)).thenReturn(expectedResult);

        //ACT
        Home actualResult = this.homeService.createHome(expectedResult);

        //ASSERT
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void getHomeByIdSuccessful() {

        //ASSIGN
        Integer homeId = 1;

        Home expectedResult = new Home();
        expectedResult.setId(1);
        expectedResult.setName("Winterfell");
        expectedResult.setSizeSqrFt(1000);
        expectedResult.setOccupiedSqrFt(0);

        Mockito.when(this.homeDao.findById(homeId)).thenReturn(Optional.of(expectedResult));

        //ACT
        Home actualResult = this.homeService.getHomeById(homeId);

        //ASSERT
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void getHomeByIdNotSuccessful() {

        //ASSIGN
        Integer homeId = 2;

        Home expectedResult = null;

        Mockito.when(this.homeDao.findById(homeId)).thenReturn(Optional.empty());

        //ACT
        Home actualResult = this.homeService.getHomeById(homeId);

        //ASSERT
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void deleteHomeSuccessful() {

        //ASSIGN
        Integer homeId = 1;

        Home expectedResult = new Home();
        expectedResult.setId(1);
        expectedResult.setName("Winterfell");
        expectedResult.setSizeSqrFt(1000);
        expectedResult.setOccupiedSqrFt(0);

        Mockito.when(this.homeDao.findById(homeId)).thenReturn(Optional.of(expectedResult));

        //ACT
        Home actualResult = this.homeService.deleteHome(homeId);

        //ASSERT
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void deleteHomeUnsuccessful() {

        //ASSIGN
        Integer homeId = 1;

        Home expectedResult = null;

        Mockito.when(this.homeDao.findById(homeId)).thenReturn(Optional.empty());

        //ACT
        Home actualResult = this.homeService.deleteHome(homeId);

        //ASSERT
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void updateHomeSuccessful() {

        //ASSIGN
        Integer homeId = 1;

        Home originalHome = new Home();
        originalHome.setId(homeId);
        originalHome.setName("Winterfell");
        originalHome.setSizeSqrFt(1000);
        originalHome.setOccupiedSqrFt(400);

        Home updatedHome = new Home();
        updatedHome.setId(homeId);
        updatedHome.setName("Summerhall");
        updatedHome.setSizeSqrFt(1200);
        updatedHome.setOccupiedSqrFt(500);

        Mockito.when(this.homeDao.findById(homeId)).thenReturn(Optional.of(originalHome));
        Mockito.when(this.homeDao.save(updatedHome)).thenReturn(updatedHome);

        Home expectedResult = updatedHome;

        //ACT
        Home actualResult = this.homeService.updateHome(updatedHome);

        //ASSERT
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void updateHomeNotSuccessful() {

        //ASSIGN
        Integer homeId = 1;

        Home updatedHome = new Home();
        updatedHome.setId(homeId);
        updatedHome.setName("Summerhall");
        updatedHome.setSizeSqrFt(1200);
        updatedHome.setOccupiedSqrFt(500);

        Mockito.when(this.homeDao.findById(homeId)).thenReturn(Optional.empty());

        Home expectedResult = null;

        //ACT
        Home actualResult = this.homeService.updateHome(updatedHome);

        //ASSERT
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void violatesSizeConstraints() {

        //ASSIGN
        Home home1 = new Home();
        home1.setSizeSqrFt(1000);
        home1.setOccupiedSqrFt(501);

        Home home2 = new Home();
        home2.setSizeSqrFt(1000);
        home2.setOccupiedSqrFt(500);

        Home home3 = new Home();
        home3.setSizeSqrFt(1000);
        home3.setOccupiedSqrFt(30);

        //ACT
        Boolean result1 = this.homeService.violatesSizeConstraints(home1);
        Boolean result2 = this.homeService.violatesSizeConstraints(home2);
        Boolean result3 = this.homeService.violatesSizeConstraints(home3);

        //ASSERT
        assertTrue(result1);
        assertFalse(result2);
        assertFalse(result3);
    }
}