package com.example.ruler;

import com.example.ruler.entity.Planet;
import com.example.ruler.entity.Ruler;
import com.example.ruler.repository.PlanetRepo;
import com.example.ruler.service.PlanetService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class PlanetServiceTest {
    @Autowired
    private PlanetService planetService;

    @MockBean
    private PlanetRepo planetRepo;

    @Test
    public void addPlanet(){
        Planet planet = new Planet();
        boolean isCreated = planetService.addPlanet(planet);
        Assertions.assertTrue(isCreated);
        Mockito.verify(planetRepo,Mockito.times(1)).save(ArgumentMatchers.any(Planet.class));
    }

    @Test
    public void addPlanetFail(){
        Planet planet = new Planet();
        planet.setName("test");
        Mockito.doReturn(new Planet()).when(planetRepo).findByName("test");
        boolean isCreated = planetService.addPlanet(planet);
        Assertions.assertFalse(isCreated);
        Mockito.verify(planetRepo,Mockito.times(0)).save(ArgumentMatchers.any(Planet.class));
    }


}
