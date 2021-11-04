package com.example.ruler.repository;

import com.example.ruler.entity.Planet;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlanetRepo extends CrudRepository<Planet, Long> {
    Planet findByName(String name);
    @Query("select p from Planet p where p.name is not null")
    List<Planet> getAll();
}
