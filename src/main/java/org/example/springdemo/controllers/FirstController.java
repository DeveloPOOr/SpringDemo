package org.example.springdemo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class FirstController {

    @GetMapping("/hello")
    public String sayHello(@RequestParam(name = "name", required = false) String name, @RequestParam(name = "surname", required = false) String surname, Model model) {
        System.out.println("Hello " + name + " " + surname);
        model.addAttribute("message", "Hello " + name + " " + surname);
        return "/first/hello";
    }


    @GetMapping("/goodbye")
    public String sayBye(HttpServletRequest request) {
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        System.out.println("Goodbye " + name + " " + surname);
        return "/first/goodbye";
    }

    @GetMapping("first/calculator")
    public String calculate(@RequestParam(name = "a") int a,
                            @RequestParam(name = "b") int b,
                            @RequestParam(name = "operation") String operation,
                            Model model) {
        int ans = 0;
        String oper = null;
        if(operation.equals("multiplication")) {
            oper = "*";
            ans = a*b;
        }

        if(operation.equals("addition")) {
            oper = "+";
            ans = a+b;
        }

        if(operation.equals("subtraction")) {
            oper = "-";
            ans = a-b;
        }

        if(operation.equals("division")) {
            oper = "/";
            ans = a/b;
        }

        model.addAttribute("message", a + " " + oper + " " + b +  " = " + ans);

        return "first/calculator";
    }
}
