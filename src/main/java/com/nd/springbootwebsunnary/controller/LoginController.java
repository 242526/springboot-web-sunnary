package com.nd.springbootwebsunnary.controller;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class LoginController {


    @PostMapping(value = "/login")
//    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(@RequestParam(name = "username",required = true) String username,
                        @RequestParam(name = "password",required = true) String password,
                        Map<String,Object> map,
                        HttpSession session){
        if (!StringUtils.isEmpty(username)&&"123456".equals(password)){
            //登入成功吧，username存入到session域中
            session.setAttribute("username",username);
            //重定向到主页 /当前项目下
            return "redirect:/main.html";
        }
        map.put("msg","登入失败！");
        return "login";
    }
}
