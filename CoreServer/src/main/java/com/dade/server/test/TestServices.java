package com.dade.server.test;

import com.dade.common.domain.HunterUser.HunterUser;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Dade on 2017/2/22.
 */
@Service
public class TestServices {

    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "addServiceFallbackString")
    public String addService() {
        return restTemplate.getForEntity("http://mongo-server/api/hunter/add", String.class).getBody();
    }

    @HystrixCommand(fallbackMethod = "addServiceFallbackHunterUser")
    public HunterUser addServicePost(HunterUser hunterUser) {
        final String uri = "http://mongo-server/api/hunter/add_post";
        return restTemplate.postForObject( uri, hunterUser, HunterUser.class);
    }

    public String addServiceFallbackString() {
        return "error";
    }

    public HunterUser addServiceFallbackHunterUser(HunterUser hunterUser) {
        hunterUser.setName("error");
        hunterUser.setAge(0);
        return hunterUser;
    }

}
