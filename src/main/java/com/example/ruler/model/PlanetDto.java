package com.example.ruler.model;

import com.example.ruler.entity.Planet;
import com.example.ruler.entity.Ruler;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class PlanetDto {
    private Long id;
    private String name;

    public PlanetDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public static PlanetDto valueOf(Planet planet){
        return new PlanetDto(
                planet.getId(),
                planet.getName()
        );
    }
}
