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

    public Home createHome(Home home){
        return this.homeDao.save(home);
    }

    public Home getHomeById(Integer id){
        return this.homeDao.findById(id).orElse(null);
    }

    public Home deleteHome(Integer id){

        /*
        Check that the Home with the given ID exists in the database.
        If it does, delete it and return it.
        If not, then just return null.
         */
        Home checkHome = this.homeDao.findById(id).orElse(null);
        if(checkHome != null){
            this.homeDao.deleteById(id);
            return checkHome;
        }
        return null;
    }

    public Home updateHome(Home home){

        /*
        Check that a Home with the same ID exists in the database.
        If it does, update that home with the new values and return the updated Home.
        If not, return null.
         */
        Home checkHome = this.homeDao.findById(home.getId()).orElse(null);
        if(checkHome != null){
            //checking the size of the home is done in the violatesSizeConstraints method
            checkHome.setName(home.getName());
            checkHome.setSizeSqrFt(home.getSizeSqrFt());
            checkHome.setOccupiedSqrFt(home.getOccupiedSqrFt());
            return this.homeDao.save(checkHome);
        }
        return null;
    }

    public Boolean violatesSizeConstraints(Home home){
        return (home.getOccupiedSqrFt() > home.getSizeSqrFt() / 2);
    }
}
