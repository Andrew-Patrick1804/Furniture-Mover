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
}