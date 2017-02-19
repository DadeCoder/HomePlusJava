package com.dade.server.test;

import com.dade.common.utils.LogUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Dade on 2017/2/19.
 */
@RestController
@RequestMapping("/api/test")
public class TestController {

    @RequestMapping("/log")
    public String testLogUtil(){
        LogUtil.info("Hello LogUtil!");
        return "log util is fine!";
    }

}
