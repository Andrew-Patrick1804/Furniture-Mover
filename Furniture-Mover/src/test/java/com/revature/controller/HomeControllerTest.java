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

        //ACT

        //ASSERT
    }

    @Test
    void getHomeById() {
    }

    @Test
    void deleteHome() {
    }

    @Test
    void updateHome() {
    }
}