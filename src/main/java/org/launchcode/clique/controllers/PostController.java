package org.launchcode.clique.controllers;

import org.launchcode.clique.models.Post;
import org.launchcode.clique.models.Users;
import org.launchcode.clique.models.data.PostDao;
import org.launchcode.clique.models.data.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

/**
 * Created by Jaurron on 7/10/2017.
 */

@Controller
@RequestMapping("post")
public class PostController {

    @Autowired
    private PostDao postDao;

    @Autowired
    private UserDao userDao;


    @RequestMapping(value = "")
    public String index(Model model){

        model.addAttribute("posts", postDao.findAll());
        model.addAttribute("title", "Posts");

        return "post/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddPostForm(Model model) {
        model.addAttribute("title", "Add Post");
        model.addAttribute(new Post());
        model.addAttribute("users", userDao.findAll());

        return "post/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddPostForm(@ModelAttribute @Valid Post newPost,
                                     Errors errors, @RequestParam int userId,
                                     Model model){

        Users user = userDao.findOne(userId);
        newPost.setUser(user);

        if(errors.hasErrors()) {
            model.addAttribute("title", "Add Post");
            return "post/add";

        }

        postDao.save(newPost);
        return "redirect:";
    }

    @RequestMapping(value = "remove", method = RequestMethod.GET)
    public String displayRemovePostForm(Model model){

        model.addAttribute("posts", postDao.findAll());
        model.addAttribute("title", "Remove Post");

        return "post/remove";

    }

    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public String processRemovePostForm(@RequestParam int[] postIds){

        for (int postId : postIds){
            postDao.delete(postId);
        }

        return "redirect:";
    }
}
