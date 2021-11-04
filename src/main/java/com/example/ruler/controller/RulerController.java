package com.example.ruler.controller;

import com.example.ruler.entity.Ruler;
import com.example.ruler.model.RulerDto;
import com.example.ruler.service.RulerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RulerController {
    private final RulerService rulerService;

    @Autowired
    public RulerController(RulerService service) {
        rulerService = service;
    }

    @PostMapping("/ruler")
    public ResponseEntity<HttpStatus> saveRuler(@RequestBody Ruler ruler){
        return rulerService.addRuler(ruler) ?
                new ResponseEntity<>(HttpStatus.CREATED):
                new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/ruler/{name}")
    public RulerDto findRuler(@PathVariable String name){
            return rulerService.getRuler(name);
    }

    @GetMapping("/youngruler")
    public List<RulerDto> findYoungRuler(){
        return rulerService.getYoungRuler();
    }

    @GetMapping("/idlers")
    public List<RulerDto> findIdlers(){
        return rulerService.getRulerByPlanetNull();
    }

    @PostMapping("/appoint")
    public ResponseEntity<HttpStatus> appointPlanet(
            @RequestParam String rulername,
            @RequestParam String planetname){
        return rulerService.appointPlanet(rulername, planetname) ?
                new ResponseEntity<>(HttpStatus.OK):
                new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}
