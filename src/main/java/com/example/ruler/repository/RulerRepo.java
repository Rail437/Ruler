package com.example.ruler.repository;

import com.example.ruler.entity.Ruler;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RulerRepo extends CrudRepository<Ruler, Long> {
    Ruler findByName(String name);
    Ruler findByAge(int age);

    @Query(value = "select r FROM Ruler r WHERE r.planets is empty")
    List<Ruler> findByPlanetsNull();

    @Query(value = "select * from Ruler order by age asc limit 10", nativeQuery = true)
    List<Ruler> findYoungTen();
}