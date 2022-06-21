package com.aircontrol.beta.contollers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class MainController {
    @GetMapping("/")
    public String viewIndexPage(Model model) {
        model.addAttribute("title", "Begin Page");
        return "index";
    }
}