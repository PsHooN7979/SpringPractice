package com.example.demo.controller;

import com.example.demo.Coffee;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/coffees")
public class DemoController {
    private List<Coffee> coffees = new ArrayList<>();

    public DemoController() {
        coffees.addAll(List.of(
                new Coffee("Cereza"),
                new Coffee("Zozia"),
                new Coffee("Ganador"),
                new Coffee("Lareno")
        ));
    }

    @GetMapping
    Iterator<Coffee> getCoffees() {
        return coffees.iterator();
    }

    @GetMapping("/{id}")
    Optional<Coffee> getCoffeeById(@PathVariable String id) {
        for(Coffee c: coffees){
            if(c.getId().equals(id)){
                return Optional.of(c);
            }
        }

        return Optional.empty();
    }

    @PostMapping
    public Coffee postCoffee(@RequestBody Coffee coffee) {
        coffees.add(coffee);
        return coffee;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Coffee> putCoffee(@PathVariable String id, @RequestBody Coffee coffee){
        int coffeeIndex = -1;

        for(Coffee c : coffees) {
            if(c.getId().equals(id)){
                coffeeIndex = coffees.indexOf(c);
                coffees.set(coffeeIndex, coffee);
            }
        }
        return (coffeeIndex == -1) ?
                new ResponseEntity<>(postCoffee(coffee), HttpStatus.CREATED) :
                new ResponseEntity<>(coffee, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteCoffee(@PathVariable String id) {
        coffees.removeIf(c -> c.getId().equals(id));
    }





}
