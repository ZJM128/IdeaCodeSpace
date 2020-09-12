package com.guigu;

import com.alibaba.fastjson.JSON;

public class PeronToJson {
    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public static void main(String[] args) {
        PeronToJson person=new PeronToJson();
        person.setAge(23);
        person.setName("zhangsan");

        // 对象转为json
        final String json = JSON.toJSONString(person);
        System.out.println(json);

        // json转为对象
        String str="{\"age\":23,\"name\":\"李四\"}";
        final PeronToJson peron = JSON.parseObject(str, PeronToJson.class);
        System.out.println(peron.getAge()+" "+peron.getName());
    }
}
