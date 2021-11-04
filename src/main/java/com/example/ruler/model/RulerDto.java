package com.example.ruler.model;

import com.example.ruler.entity.Ruler;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class RulerDto {
    private Long id;
    private String name;
    private int age;
    private List<PlanetDto> planets;

    public RulerDto() {
    }

    public RulerDto(Long id, String name, int age, List<PlanetDto> planetsList) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.planets = planetsList;
    }

    public static RulerDto valueOf(Ruler ruler){
        if(ruler != null){
        return new RulerDto(
                ruler.getId(),
                ruler.getName(),
                ruler.getAge(),
                ruler.getPlanets().stream().map(PlanetDto::valueOf).collect(Collectors.toList())
        );
        }else {
            return new RulerDto();
        }

    }

}
