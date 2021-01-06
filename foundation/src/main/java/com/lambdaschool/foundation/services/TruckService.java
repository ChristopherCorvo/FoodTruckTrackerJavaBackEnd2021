package com.lambdaschool.foundation.services;

import com.lambdaschool.foundation.models.Truck;

import java.util.List;

public interface TruckService
{
    // GET - return a list of all trucks
    List<Truck> findAll();

    Truck findTruckById(long id);

    Truck findTruckByName(String name);

    List<Truck> findTruckByNameLike(String name);

    List<Truck> findByCuisineType(String name);

    // POST - add a new Truck Object
    // Given a complete Truck object, saves that Truck object in the database.
    // If a primary key is provided, the record is completely replaced
    // If no primary key is provided, one is automatically generated and the record is added to the database.
    Truck save(Truck truck);

    // Updates the provided fields in the truck record referenced by a truckid
    Truck update(
        Truck truck,
        long id);

    // use this to DELETE a Truck record
    void delete(long id);

    void deleteAllTrucks();
}
