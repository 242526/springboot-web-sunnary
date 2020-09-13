package com.nd.springbootwebsunnary.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.Map;

@Controller
public class HelloController {

    @ResponseBody
    @RequestMapping(value = "/hello",method = RequestMethod.GET )
    public String hello(){
        return "hello";
    }



    @RequestMapping(value = "/sendHello",method = RequestMethod.GET)
    public String sendHello(Map<String,Object> map){
        map.put("msg","<h1>登入成功！</h1>");
        map.put("goods","获取成功");
        map.put("users", Arrays.asList("陈鹏涛","hello","springBoot!"));
        return "success";
    }

}
