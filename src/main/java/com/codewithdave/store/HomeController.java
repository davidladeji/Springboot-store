package com.codewithdave.store;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;;

@Controller
public class HomeController {

    @Value("${spring.application.name}")
	private String appName;

    @RequestMapping("/")
    public String index(){
        System.out.println(appName);
        return "index.html";
    }
}
