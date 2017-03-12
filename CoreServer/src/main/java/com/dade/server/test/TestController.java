package com.dade.server.test;

import com.dade.common.domain.HunterUser.HunterUser;
import com.dade.common.utils.LogUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Dade on 2017/2/19.
 */
@RestController
@RequestMapping("/api/test")
public class TestController {

    @Autowired
    TestServices services;

    @Autowired
    RestTemplate restTemplate;


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

    @RequestMapping(value = "/find/{phoneNumber}")
    public HunterUser findByPhoneNumber(@PathVariable String phoneNumber){
        final String uri = "http://mongo-server/api/hunter/find/" + phoneNumber;
        return restTemplate.getForEntity(uri, HunterUser.class).getBody();
    }

    /**
     * 用户更换头像
     * @param src
     * @param data
     * @param file
     * @return
     */
    @RequestMapping(value = "/image_head", method = RequestMethod.POST)
    Map imageHead(@RequestParam("avatar_src") String src,
                  @RequestParam("avatar_data") String data,
                  @RequestParam("avatar_file") MultipartFile file){

        //判断文件的MIMEtype
        String type = file.getContentType();

        String imageHeadUrl = services.imageHead(src, data, file);
        if(type==null || !type.toLowerCase().startsWith("image/") || imageHeadUrl == null)
            return  new HashMap<String, Object>(){
                {
                    put("state", 403);
                }
            };
        else
            return  new HashMap<String, Object>(){
                {
                    LogUtil.info(imageHeadUrl);
                    put("state", 200);
                    put("message","message");
                    put("result", imageHeadUrl);
                }
            };

    }


}
