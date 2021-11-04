package com.example.ruler.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Ruler {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private int age;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "planet_id")
    private List<Planet> planets;

    public void setPlanets(Planet planet) {
        if(planets == null){
            planets = new ArrayList<>();
            planets.add(planet);
            return;
        }
        this.planets.add(planet);
    }

    public Ruler() {
    }
}
