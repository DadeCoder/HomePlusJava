package com.dade.mongo.controller;

import com.dade.common.domain.HunterUser.HunterUser;
import com.dade.common.utils.LogUtil;
import com.dade.mongo.dao.HunterUser.HunterUserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;


/**
 * Created by Dade on 2017/2/22.
 */
@RestController
@RequestMapping("/api/hunter")
public class HunterUserController {

    @Autowired
    HunterUserDao dao;


    @RequestMapping("/hello")
    String hello(){
        LogUtil.info("hello mongo server!");
        return "hello";
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    HunterUser user(@RequestBody HunterUser hunterUser){
        hunterUser = dao.addOne(hunterUser);
        return hunterUser;
    }

    @RequestMapping("/add")
    String add(){
        return "spring cloud is final!";
    }

    @RequestMapping(value = "/add_post", method = RequestMethod.POST)
    HunterUser addPost(@RequestBody HunterUser hunterUser){
        return dao.insert(hunterUser);
    }

    @RequestMapping("/find/{phoneNumber}")
    HunterUser findByPhoneNumber(@PathVariable String phoneNumber){

        HunterUser dbUser = dao.findByPhoneNumber(phoneNumber);
        return dbUser;
    }

}
