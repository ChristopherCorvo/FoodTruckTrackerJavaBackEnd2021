package com.lambdaschool.foundation.repository;

import com.lambdaschool.foundation.models.Truck;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TruckRepository extends CrudRepository<Truck, Long>
{

    // Find Truck by name
    Truck findByName(String name);

    List<Truck> findByNameContainingIgnoreCase(String name);

    List<Truck> findByCuisineType(String name);
}
