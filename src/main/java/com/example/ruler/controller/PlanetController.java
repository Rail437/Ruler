package com.example.ruler.controller;

import com.example.ruler.entity.Planet;
import com.example.ruler.model.PlanetDto;
import com.example.ruler.service.PlanetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PlanetController {
    private final PlanetService service;


    @GetMapping("/planets")
    public List<PlanetDto> getAllPlanet(){
        return service.getAllPlanet();
    }

    @PostMapping("/planet")
    public ResponseEntity<HttpStatus> addPlanet(@RequestBody Planet planet){
        return service.addPlanet(planet) ?
                new ResponseEntity<>(HttpStatus.CREATED):
                new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/planet/{id}")
    public ResponseEntity<HttpStatus> deletePlanet(@PathVariable Long id){
        return service.deletePlanet(id) ?
                new ResponseEntity<>(HttpStatus.CREATED):
                new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
