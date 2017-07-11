package org.launchcode.clique.controllers;

import org.launchcode.clique.models.Clique;
import org.launchcode.clique.models.Post;
import org.launchcode.clique.models.data.CliqueDao;
import org.launchcode.clique.models.data.PostDao;
import org.launchcode.clique.models.forms.AddCliqueItemForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

/**
 * Created by Jaurron on 7/10/2017.
 */

@Controller
@RequestMapping("clique")
public class CliqueController {

    @Autowired
    private CliqueDao cliqueDao;

    @Autowired
    private PostDao postDao;

    @RequestMapping(value = "")
    public String index(Model model) {
        model.addAttribute("clique", cliqueDao.findAll());
        model.addAttribute("title", "Cliques");

        return "clique/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(Model model){
        model.addAttribute(new Clique());
        model.addAttribute("title", "Add A Clique");

        return "clique/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String add(Model model, @ModelAttribute @Valid Clique clique,
                      Errors errors){

        if(errors.hasErrors()){
            model.addAttribute(clique);
            return "clique/add";
        }

        cliqueDao.save(clique);
        return "redirect:view/" + clique.getId();
    }

    @RequestMapping(value = "view/{cliqueId}", method = RequestMethod.GET)
    public String viewClique(Model model, @PathVariable("cliqueId") int id){

        Clique clique = cliqueDao.findOne(id);

        model.addAttribute("title", clique.getName());
        model.addAttribute("clique", clique);

        return "clique/view";
    }

    @RequestMapping(value = "add-post/{cliqueId}", method = RequestMethod.GET)
    public String addPost(Model model, @PathVariable("cliqueId") int id){
        Clique clique = cliqueDao.findOne(id);
        AddCliqueItemForm newCliqueItemForm = new AddCliqueItemForm(clique, postDao.findAll());
        model.addAttribute("form", newCliqueItemForm);
        model.addAttribute("title", "Add post to Clique:" + clique.getName());

        return "clique/add-post";
    }

    @RequestMapping(value = "add-post/{cliqueId}", method = RequestMethod.POST)
    public String addPost(Model model, @ModelAttribute @Valid AddCliqueItemForm addCliqueItemForm,
                          Errors errors, @PathVariable("cliqueId") int id){

        if(errors.hasErrors()){
            model.addAttribute(addCliqueItemForm);
            return "clique/add-post";
        }

        Post post = postDao.findOne(addCliqueItemForm.getPostId());
        Clique clique = cliqueDao.findOne(addCliqueItemForm.getCliqueId());

        clique.addItem(post);
        cliqueDao.save(clique);

        return "redirect:/clique/view/" + clique.getId();
    }
}
