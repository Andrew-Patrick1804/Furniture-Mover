package com.revature.controller;

import com.revature.models.Home;
import com.revature.models.Response;
import com.revature.services.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("homeController")
@RequestMapping("home")
public class HomeController {

    private HomeService homeService;

    @Autowired
    public HomeController(HomeService homeService){
        this.homeService = homeService;
    }

    @PostMapping
    public Response createHome(@RequestBody Home home){
        Home newHome = this.homeService.createHome(home);
        return new Response(true, "New Home Created", newHome);
    }

    @GetMapping
    public Response getAllHomes(){
        List<Home> homes = this.homeService.getAllHomes();
        return new Response(true, "Retrieved All Homes", homes);
    }

    @GetMapping("{homeId}")
    public Response getHomeById(@PathVariable Integer homeId){
        Home home = this.homeService.getHomeById(homeId);
        if(home == null){
            return new Response(false, "No Home With That ID Was Found", null);
        }
        else{
            return new Response(true, "Home Found With Given ID", home);
        }
    }

    @DeleteMapping("{homeId}")
    public Response deleteHome(@PathVariable Integer homeId){
        Home home = this.homeService.deleteHome(homeId);
        if(home == null){
            return new Response(false, "Home Not Deleted Because It Does Not Exist", null);
        }
        else{
            return new Response(true, "Home With Given ID Deleted", home);
        }
    }

    @PutMapping
    public Response updateHome(@RequestBody Home home){
        if(this.homeService.violatesSizeConstraints(home)){
            return new Response(false, "Size Constraints Violated", null);
        }
        Home updatedHome = this.homeService.updateHome(home);
        if(updatedHome == null){
            return new Response(false, "Home Does Not Exist", null);
        }
        else{
            return new Response(true, "Home Updated", updatedHome);
        }
    }
}
