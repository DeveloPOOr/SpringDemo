package org.example.springdemo.controllers;

import org.example.springdemo.dao.PersonDao;
import org.example.springdemo.model.Person;
import org.example.springdemo.model.Role;
import org.example.springdemo.model.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.logging.Logger;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private PersonDao personDao;

    Logger logger = Logger.getLogger(String.valueOf(AuthController.class));

    @GetMapping("/login")
    public String login() {
        return "/auth/login";
    }


    @GetMapping("/register")
    public String registerPage(@ModelAttribute("person") Person person) {
        return "/auth/register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("person") @Valid Person person,
                            BindingResult bindingResult) {

        if(bindingResult.hasErrors())
            return "people/addPerson";

        person.setPassword(passwordEncoder.encode(person.getPassword()));
        person.setRole(Role.USER);
        person.setStatus(Status.ACTIVE);

        personDao.save(person);
        logger.info("New user was saved");
        return "redirect:/people";
    }


}
