package com.dade.server.test;

import com.dade.common.domain.HunterUser.HunterUser;
import com.dade.common.utils.LogUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Dade on 2017/2/19.
 */
@RestController
@RequestMapping("/api/test")
public class TestController {

    @Autowired
    TestServices services;

    @RequestMapping("/log")
    public String testLogUtil(){
        LogUtil.info("Hello LogUtil!");
        return "log util is fine!";
    }

    @RequestMapping(value = "/add")
    public String add(){
        return services.addService();
    }

    @RequestMapping(value = "/add_post", method = RequestMethod.POST)
    public HunterUser addPost(@RequestBody HunterUser hunterUser){
        return services.addServicePost(hunterUser);
    }

}
