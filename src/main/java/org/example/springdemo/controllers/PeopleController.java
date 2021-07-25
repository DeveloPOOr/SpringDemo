package org.example.springdemo.controllers;

import org.example.springdemo.dao.PersonDao;
import org.example.springdemo.model.Person;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@Controller
@RequestMapping("/people")
public class PeopleController {

    private final PersonDao personDao;

    Logger logger = Logger.getLogger(String.valueOf(PeopleController.class));

    @Autowired
    public PeopleController(PersonDao personDao) {
        this.personDao = personDao;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("people", personDao.index());
        return "/people/index";
    }

    @GetMapping("/{id}")
    public String show(Model model, @PathVariable(name = "id") int id) {
        model.addAttribute("person", personDao.show(id));
        return "/people/show";
    }

    @GetMapping("/new")
    public String addPerson(Model model) {
        model.addAttribute("person", new Person());
        logger.info("Empty model was added");
        model.addAttribute("people", personDao.index());
        return "/people/addPerson";
    }

    @PostMapping()
    public String saveUser(@ModelAttribute("person") Person person) {
        personDao.save(person);
        logger.info("New user was saved");
        return "redirect:/people";
    }
}
