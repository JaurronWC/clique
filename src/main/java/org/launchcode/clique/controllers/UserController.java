package org.launchcode.clique.controllers;

import org.launchcode.clique.models.Users;
import org.launchcode.clique.models.data.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

/**
 * Created by Jaurron on 7/11/2017.
 */

@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserDao userDao;

    @RequestMapping(value = "")
    public String index(Model model){

        model.addAttribute("users", userDao.findAll());
        model.addAttribute("title", "Users");

        return "user/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddUserForm(Model model){

        model.addAttribute(new Users());
        model.addAttribute("title", "Add Users");

        return "user/add";

    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String add(Model model,
                      @ModelAttribute @Valid Users users, Errors errors){

        if(errors.hasErrors()){
            model.addAttribute("title", "Add Users");
            return "user/add";
        }

        userDao.save(users);
        return "redirect:";
    }
}
