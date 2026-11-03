package com.cifpcesarmanrique.HolaMundo.controllers;

import com.cifpcesarmanrique.HolaMundo.controllers.Models.persona;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping("/persona")
    public String nuestraPersona(Model model) {

        persona p1 = new persona(1, "Antonio", 24, "antonio@gmail.com" );
        model.addAttribute("persona",p1);

        return "index";
    }
}
