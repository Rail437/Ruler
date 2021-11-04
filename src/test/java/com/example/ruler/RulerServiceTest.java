package com.example.ruler;

import com.example.ruler.entity.Planet;
import com.example.ruler.entity.Ruler;
import com.example.ruler.repository.PlanetRepo;
import com.example.ruler.repository.RulerRepo;
import com.example.ruler.service.RulerService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatcher;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class RulerServiceTest {
    @Autowired
    private RulerService rulerService;

    @MockBean
    private RulerRepo rulerRepo;

    @MockBean
    private PlanetRepo planetRepo;

    @Test
    public void addRuler(){
        Ruler ruler = new Ruler();
        boolean isCreated = rulerService.addRuler(ruler);
        Assertions.assertTrue(isCreated);
        Mockito.verify(rulerRepo,Mockito.times(1)).save(ArgumentMatchers.any(Ruler.class));
    }

    @Test
    public void addRulerFatal(){
        Ruler ruler = new Ruler();
        ruler.setName("test");
        Mockito.doReturn(new Ruler()).when(rulerRepo).findByName("test");
        boolean isNotCreated = rulerService.addRuler(ruler);
        Assertions.assertFalse(isNotCreated);
        Mockito.verify(rulerRepo,Mockito.times(0)).save(ArgumentMatchers.any(Ruler.class));
    }

    @Test
    public void appointPlanetCheck(){
        Ruler ruler = new Ruler();
        ruler.setName("test");
        Planet planet = new Planet();
        planet.setName("test");

        Mockito.doReturn(new Ruler()).when(rulerRepo).findByName("test");
        Mockito.doReturn(new Planet()).when(planetRepo).findByName("test");

        boolean isCreated = rulerService.appointPlanet("test","test");
        Assertions.assertTrue(isCreated);
        Mockito.verify(rulerRepo,Mockito.times(1)).save(ArgumentMatchers.any(Ruler.class));
        Mockito.verify(planetRepo,Mockito.times(1)).save(ArgumentMatchers.any(Planet.class));
    }

    @Test
    public void appointPlanetCheckFail(){
        Ruler ruler = new Ruler();
        ruler.setName("test");
        Planet planet = new Planet();
        planet.setName("test");

        Mockito.doReturn(new Ruler()).when(rulerRepo).findByName("test");
        Mockito.doReturn(new Planet()).when(planetRepo).findByName("test");

        boolean isCreated = rulerService.appointPlanet("","test");
        Assertions.assertFalse(isCreated);
        boolean isNotCreated = rulerService.appointPlanet("test","");
        Assertions.assertFalse(isNotCreated);
        boolean notCreated = rulerService.appointPlanet("","");
        Assertions.assertFalse(notCreated);
        Mockito.verify(rulerRepo,Mockito.times(0)).save(ArgumentMatchers.any(Ruler.class));
        Mockito.verify(planetRepo,Mockito.times(0)).save(ArgumentMatchers.any(Planet.class));
    }

}
