package com.dade.common.domain.HunterUser;

import com.dade.common.basic.BasicModelObject;

/**
 * Created by Dade on 2017/2/22.
 */
public class HunterUser extends BasicModelObject {

    String id;
    String name;

    Integer age;

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

    @Override
    public String toString() {
        return "HunterUser{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
