package com.dade.common.domain.HunterUser;

import com.dade.common.basic.BasicModelObject;

/**
 * Created by Dade on 2017/2/22.
 */
public class HunterUser extends BasicModelObject {

    private String id;
    private String name;

    private Integer age;

    private String imageHeaderUrl;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getImageHeaderUrl() {
        return imageHeaderUrl;
    }

    public void setImageHeaderUrl(String imageHeaderUrl) {
        this.imageHeaderUrl = imageHeaderUrl;
    }

    @Override
    public String toString() {
        return "HunterUser{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
