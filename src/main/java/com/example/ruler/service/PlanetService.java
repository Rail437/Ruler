package com.example.ruler.service;

import com.example.ruler.entity.Planet;
import com.example.ruler.model.PlanetDto;
import com.example.ruler.repository.PlanetRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class PlanetService {
    private final PlanetRepo planetRepo;

    public List<PlanetDto> getAllPlanet() {
        return planetRepo.getAll().stream().map(PlanetDto::valueOf).collect(Collectors.toList());
    }


    public boolean addPlanet(Planet planet) {
        Planet box = planetRepo.findByName(planet.getName());
        if (box == null) {
            planetRepo.save(planet);
            return true;
        }
        return false;
    }


    public boolean deletePlanet(Long id) {
        Optional<Planet> box = planetRepo.findById(id);
        if (box.isPresent()) {
            planetRepo.deleteById(id);
            return true;
        }
        return false;
    }


}
