package org.launchcode.clique.controllers;

import org.launchcode.clique.models.data.CliqueDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Jaurron on 7/10/2017.
 */

@Controller
@RequestMapping("clique")
public class CliqueController {

    @Autowired
    private CliqueDao cliqueDao;

    @RequestMapping(value = "")
    public String index(Model model) {
        model.addAttribute("clique", cliqueDao.findAll());
        model.addAttribute("title", "Cliques");

        return "clique/index";
    }
}
