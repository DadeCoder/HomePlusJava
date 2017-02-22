package com.dade.server.test;

import com.dade.common.domain.HunterUser.HunterUser;
import com.dade.common.utils.ImageUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.apache.commons.io.FilenameUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

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

    /**
     * user change their headimage
     * TODO how to bind user id
     * @param src
     * @param data
     * @param file
     * @return
     */
    public String imageHead(String src, String data, MultipartFile file){

        JSONObject joData = new JSONObject(data);
        // 用户经过剪辑后的图片的大小
        double x = joData.getDouble("x");
        double y = joData.getDouble("y");
        double w =  joData.getDouble("width");
        double h =  joData.getDouble("height");

        String imageHeadUrl = null;

        try {
            DateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");
            String name = df.format(new Date());

            Random random = new Random();
            for(int i = 0 ;i<3 ;i++){
                name += random.nextInt(10);
            }

            // 文件后缀名称
            String ext = FilenameUtils.getExtension(file.getOriginalFilename());
            String url = "E:/foreground/homeplus/angular-seed/app/upload/";

            String path = name + "." + ext;
            File pic = new File(url, path);
            if(!pic.exists()){
                pic.mkdirs();
                InputStream is = file.getInputStream();
                ImageUtil.cut(is, pic, (int)x, (int)y, (int)w, (int)h);
                is.close();
            }
            imageHeadUrl = ".\\upload\\" + path;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return imageHeadUrl;
    }


}
