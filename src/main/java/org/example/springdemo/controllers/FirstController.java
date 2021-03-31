package org.example.springdemo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class FirstController {

    @GetMapping("/hello")
    public String sayHello(@RequestParam(name = "name", required = false) String name, @RequestParam(name = "surname", required = false) String surname) {
        System.out.println("Hello " + name + " " + surname);
        return "/first/hello";
    }


    @GetMapping("/goodbye")
    public String sayBye(HttpServletRequest request) {
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        System.out.println("Goodbye " + name + " " + surname);
        return "/first/goodbye";
    }
}
