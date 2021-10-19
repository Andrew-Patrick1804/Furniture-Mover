package com.revature.controller;

import com.revature.models.Home;
import com.revature.models.Response;
import com.revature.services.HomeService;
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
class HomeControllerTest {

    HomeController homeController;

    @Mock
    HomeService homeService;

    @BeforeEach
    void setUp() {
        this.homeController = new HomeController(homeService);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void createHome() {

        //ASSIGN
        Home home = new Home();
        home.setId(1);
        home.setName("Winterfell");
        home.setSizeSqrFt(1000);

        Response expectedResult = new Response(true, "New Home Created", home);

        Mockito.when(this.homeService.createHome(home)).thenReturn(home);

        //ACT
        Response actualResult = this.homeController.createHome(home);

        //ASSERT
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void getAllHomes() {

        //ASSIGN
        Home home1 = new Home();
        home1.setId(1);
        home1.setName("Winterfell");
        home1.setSizeSqrFt(1000);

        Home home2 = new Home();
        home2.setId(2);
        home2.setName("Dragonstone");
        home2.setSizeSqrFt(2000);

        Home home3 = new Home();
        home3.setId(3);
        home3.setName("Highgarden");
        home3.setSizeSqrFt(3000);

        List<Home> homes = new ArrayList<>();
        homes.add(home1);
        homes.add(home2);
        homes.add(home3);

        Response expectedResult = new Response(true, "Retrieved All Homes", homes);

        Mockito.when(this.homeService.getAllHomes()).thenReturn(homes);

        //ACT
        Response actualResult = this.homeController.getAllHomes();

        //ASSERT
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void getHomeByIdFound() {

        //ASSIGN
        Integer homeId = 1;

        Home home = new Home();
        home.setId(1);
        home.setName("Winterfell");
        home.setSizeSqrFt(1000);

        Response expectedResult = new Response(true, "Home Found With Given ID", home);

        Mockito.when(this.homeService.getHomeById(homeId)).thenReturn(home);

        //ACT
        Response actualResult = this.homeController.getHomeById(homeId);

        //ASSERT
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void getHomeByIdNotFound() {

        //ASSIGN
        Integer homeId = 2;

        Response expectedResult = new Response(false, "No Home With That ID Was Found", null);

        Mockito.when(this.homeService.getHomeById(homeId)).thenReturn(null);

        //ACT
        Response actualResult = this.homeController.getHomeById(homeId);

        //ASSERT
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void deleteHomeSuccessful() {

        //ASSIGN
        Integer homeId = 1;

        Home home = new Home();
        home.setId(1);
        home.setName("Winterfell");
        home.setSizeSqrFt(1000);

        Response expectedResult = new Response(true, "Home With Given ID Deleted", home);

        Mockito.when(this.homeService.deleteHome(homeId)).thenReturn(home);

        //ACT
        Response actualResult = this.homeController.deleteHome(homeId);

        //ASSERT
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void deleteHomeNotSuccessful() {

        //ASSIGN
        Integer homeId = 2;

        Response expectedResult = new Response(false, "Home Not Deleted Because It Does Not Exist", null);

        Mockito.when(this.homeService.deleteHome(homeId)).thenReturn(null);

        //ACT
        Response actualResult = this.homeController.deleteHome(homeId);

        //ASSERT
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void updateHomeSuccessful() {

        //ASSIGN
        Home updatedHome = new Home();
        updatedHome.setId(1);
        updatedHome.setName("Winterfell");
        updatedHome.setSizeSqrFt(1000);
        updatedHome.setOccupiedSqrFt(500);

        Response expectedResult = new Response(true, "Home Updated", updatedHome);

        Mockito.when(this.homeService.violatesSizeConstraints(updatedHome)).thenReturn(false);
        Mockito.when(this.homeService.updateHome(updatedHome)).thenReturn(updatedHome);

        //ACT
        Response actualResult = this.homeController.updateHome(updatedHome);

        //ASSERT
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void updateHomeNotSuccessful() {

        //ASSIGN
        Home updatedHome = new Home();
        updatedHome.setId(3);
        updatedHome.setName("Winterfell");
        updatedHome.setSizeSqrFt(1000);
        updatedHome.setOccupiedSqrFt(500);

        Response expectedResult = new Response(false, "Home Does Not Exist", null);

        Mockito.when(this.homeService.violatesSizeConstraints(updatedHome)).thenReturn(false);
        Mockito.when(this.homeService.updateHome(updatedHome)).thenReturn(null);

        //ACT
        Response actualResult = this.homeController.updateHome(updatedHome);

        //ASSERT
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void updateHomeNotSuccessfulSizeViolated() {

        //ASSIGN
        Home updatedHome = new Home();
        updatedHome.setId(1);
        updatedHome.setName("Winterfell");
        updatedHome.setSizeSqrFt(1000);
        updatedHome.setOccupiedSqrFt(501);

        Response expectedResult = new Response(false, "Size Constraints Violated", null);

        Mockito.when(this.homeService.violatesSizeConstraints(updatedHome)).thenReturn(true);

        //ACT
        Response actualResult = this.homeController.updateHome(updatedHome);

        //ASSERT
        assertEquals(expectedResult, actualResult);
    }
}