package com.cifpcesarmanrique.HolaMundo.controllers.services;

import com.cifpcesarmanrique.HolaMundo.controllers.Models.Animal;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AnimalService {
    private final List<Animal> animals = new ArrayList<>();

    public AnimalService() {
        animals.add(new Animal(1, "Dodo", 30, true));
        animals.add(new Animal(2, "Tortuga", 100, false));
        animals.add(new Animal(3, "Lince", 15, false));
    }

    public List<Animal> findAll() {
        return animals;
    }

    public Animal findById(Integer id) {
        return animals.stream()
                .filter(animal -> animal.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public void save(Animal animal) {
        if (animal.getId() == null) {
            int nextId = animals.stream().mapToInt(Animal::getId).max().orElse(0) + 1;
            animal.setId(nextId);
            animals.add(animal);
        } else {
            int index = -1;
            for (int i = 0; i < animals.size(); i++) {
                if (animals.get(i).getId().equals(animal.getId())) {
                    index = i;
                    break;
                }
            }
            if (index != -1) {
                animals.set(index, animal);
            }
        }
    }

    public void delete(Integer id) {
        animals.removeIf(animal -> animal.getId().equals(id));
    }
}