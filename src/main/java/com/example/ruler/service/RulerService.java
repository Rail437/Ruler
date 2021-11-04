package com.example.ruler.service;

import com.example.ruler.entity.Planet;
import com.example.ruler.entity.Ruler;
import com.example.ruler.model.RulerDto;
import com.example.ruler.repository.PlanetRepo;
import com.example.ruler.repository.RulerRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RulerService {
    private final RulerRepo rulerRepo;
    private final PlanetRepo planetRepo;

    public boolean addRuler(Ruler ruler) {
        Ruler rulerFind = rulerRepo.findByName(ruler.getName());
        if(rulerFind == null){
           rulerRepo.save(ruler);
           return true;
        }
        return false;
    }

    public RulerDto getRuler(String name){
        Ruler rulerFind = rulerRepo.findByName(name);
        if(rulerFind != null){
            return RulerDto.valueOf(rulerFind);
        }
        return new RulerDto();
    }

    public List<RulerDto> getRulerByPlanetNull(){
        return rulerRepo.findByPlanetsNull().stream().map(RulerDto::valueOf).collect(Collectors.toList());

    }

    public List<RulerDto> getYoungRuler() {
        return rulerRepo.findYoungTen().stream().map(RulerDto::valueOf).collect(Collectors.toList());
    }

    public boolean appointPlanet(String rulername, String planetname) {
        Ruler ruler = rulerRepo.findByName(rulername);
        Planet planet = planetRepo.findByName(planetname);
        if( ruler != null & planet != null){
           if(planet.getRuler() == null){
               ruler.setPlanets(planet);
               planet.setRuler(ruler);
               rulerRepo.save(ruler);
               planetRepo.save(planet);
               return true;
           }
        }
        return false;
    }
}
