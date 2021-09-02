package org.example.springdemo.controllers;



import javax.validation.Valid;

import org.example.springdemo.dao.PersonDao;
import org.example.springdemo.model.Person;


import org.example.springdemo.model.Role;
import org.example.springdemo.model.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@Controller
@RequestMapping("/people")
public class PeopleController {

    private final PersonDao personDao;

    Logger logger = Logger.getLogger(String.valueOf(PeopleController.class));

    @Autowired
    private PasswordEncoder passwordEncoder;

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
    public String addPerson(@ModelAttribute("person") @Valid Person person,
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

    @GetMapping("{id}/edit")
    public String editView(@PathVariable(name = "id") int id, Model model ) {
        model.addAttribute("person", personDao.show(id));
        return "people/edit";
    }

    @PatchMapping("{id}/edit")
    public String edit(@ModelAttribute("person") @Valid Person person,
                       BindingResult bindingResult) {
        if(bindingResult.hasErrors())
            return "people/edit";

        logger.info("Person id is" + person.getId());
        personDao.update(person);
        return "redirect:/people";
    }

    @DeleteMapping("{id}/delete")
    public String delete(@ModelAttribute("person") Person person) {
        personDao.delete(person);
        return "redirect:/people";
    }



}
