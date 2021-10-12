package com.revature.services;

import com.revature.models.Home;
import com.revature.repository.HomeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("homeService")
public class HomeService {
    private HomeDao homeDao;

    @Autowired
    public HomeService(HomeDao homeDao){
        this.homeDao = homeDao;
    }

    public List<Home> getAllHomes(){
        return this.homeDao.findAll();
    }
}
