package com.cifpcesarmanrique.HolaMundo.controllers;

import com.cifpcesarmanrique.HolaMundo.controllers.Models.Animal;
import com.cifpcesarmanrique.HolaMundo.controllers.services.AnimalService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/animals")
public class AnimalController {

    private final AnimalService service;

    public AnimalController(AnimalService service) {
        this.service = service;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("animals", service.findAll());
        return "animals/index";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("animal", new Animal(null, "", 0, false));
        return "animals/form";
    }

    @PostMapping
    public String save(@Valid @ModelAttribute Animal animal, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "animals/form";
        }
        service.save(animal);
        return "redirect:/animals";
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable Integer id, Model model) {
        Animal animal = service.findById(id);
        model.addAttribute("animal", animal);
        return "animals/detail";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Integer id, Model model) {
        Animal animal = service.findById(id);
        if (animal == null) {
            return "redirect:/animals";
        }
        model.addAttribute("animal", animal);
        return "animals/form";
    }

    @PostMapping("/{id}/edit")
    public String update(@PathVariable Integer id, @Valid @ModelAttribute Animal animal, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            animal.setId(id);
            return "animals/form";
        }
        animal.setId(id);
        service.save(animal);
        return "redirect:/animals";
    }

    @GetMapping("/{id}/delete")
    public String deleteConfirm(@PathVariable Integer id, Model model) {
        Animal animal = service.findById(id);
        if (animal == null) {
            return "redirect:/animals";
        }
        model.addAttribute("animal", animal);
        return "animals/delete-confirm";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Integer id) {
        service.delete(id);
        return "redirect:/animals";
    }
}