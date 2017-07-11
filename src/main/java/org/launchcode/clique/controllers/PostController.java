package org.launchcode.clique.controllers;

import org.launchcode.clique.models.Post;
import org.launchcode.clique.models.data.PostDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Jaurron on 7/10/2017.
 */

@Controller
@RequestMapping("post")
public class PostController {

    @Autowired
    private PostDao postDao;

    @RequestMapping(value = "")
    public String index(Model model){

        model.addAttribute("posts", postDao.findAll());
        model.addAttribute("title", "Posts");

        return "post/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddPostFOrm(Model model) {
        model.addAttribute("title", "Add Post");
        model.addAttribute(new Post());

        return "post/add";
    }
}
