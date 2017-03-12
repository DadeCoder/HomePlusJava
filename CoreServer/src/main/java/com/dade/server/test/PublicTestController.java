package com.dade.server.test;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Dade on 2017/3/12.
 */
@RestController
@RequestMapping("/api/public")
public class PublicTestController {

    @RequestMapping("/test")
    String test(){
        return "public test!";
    }


}
