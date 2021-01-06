package com.lambdaschool.foundation.controllers;

import com.lambdaschool.foundation.models.Truck;
import com.lambdaschool.foundation.services.TruckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/trucks")
public class TruckController
{

    @Autowired
    private TruckService truckservice;

    // ---------------- GET Requests ---------------

    // GET all Truck objects
    // This endpoint works 1/4/21 6:52pm
    @GetMapping(value = "/trucks",
        produces = {"application/json"})
    public ResponseEntity<?> listAllTrucks()
    {
        List<Truck> myTrucks = truckservice.findAll();
        return new ResponseEntity<>(myTrucks,
            HttpStatus.OK);
    }

    // GET a Truck Obj by id
    // This endpoint works 1/4/21 6:54pm
    @GetMapping(value = "/truck/{truckid}",
        produces = {"application/json"})
    public ResponseEntity<?> getTruckById(
        @PathVariable
            Long truckid)
    {
        Truck truck = truckservice.findTruckById(truckid);
        return new ResponseEntity<>(truck, HttpStatus.OK);
    }

    // GET a Truck Obj by Name
    // This endpoint works 1/4/21 6:55pm
    @GetMapping(value = "/truck/name/{truckName}",
        produces = {"application/json"})
    public ResponseEntity<?> getTruckByName(
        @PathVariable
            String truckName)
    {
        Truck truck = truckservice.findTruckByName(truckName);
        return new ResponseEntity<>(truck, HttpStatus.OK);
    }

    // GET Truck Obj whose name contains the given substring
    // This endpoint works 1/4/21 6:57pm
    @GetMapping(value = "/trucks/namelike/{truckNameSubstring}",
        produces = {"application/json"})
    public ResponseEntity<?> listTruckNameLike(
        @PathVariable
            String truckNameSubstring)
    {
        List<Truck> myTrucks = truckservice.findTruckByNameLike(truckNameSubstring);
        return new ResponseEntity<>(myTrucks, HttpStatus.OK);
    }

    // GET Truck Objs by cuisine type
    // This endpoint works 1/4/21 6:57pm
    @GetMapping(value = "trucks/cuisinetype/{cuisinetype}",
        produces = {"application/json"})
    public ResponseEntity<?> findTrucksByCuisineType(
        @PathVariable
            String cuisinetype)
    {
        List<Truck> myTrucks = truckservice.findByCuisineType(cuisinetype);
        return new ResponseEntity<>(myTrucks, HttpStatus.OK);
    }

    // -------------- POST requests ------------------

    // POST create a truck object
    // This endpoint works 1/4/21 7:35pm
    @PostMapping(value = "/truck",
        consumes = {"application/json"},
        produces = {"application/json"})
    public ResponseEntity<?> addNewTruck(
        @Valid
        @RequestBody
            Truck newTruck)
    {
        newTruck = truckservice.save(newTruck);

        // set the location header for the newly created truck
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newTruckURI = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{truckid}")
            .buildAndExpand(newTruck.getTruckid())
            .toUri();
        responseHeaders.setLocation(newTruckURI);

        return new ResponseEntity<>(null,
            responseHeaders,
            HttpStatus.CREATED);
    }

    // ----------- PUT requests ----------------
    // - update a truck object
    // This endpoint works 1/4/21 8:20pm
    @PutMapping(value = "/truck/{truckid}")
    public ResponseEntity<?> updateTruck(
        @RequestBody
            Truck updateTruck,
        @PathVariable
            long truckid)
    {
        updateTruck.setTruckid(truckid);
        truckservice.save(updateTruck);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    // ---------- DELETE request ---------------
    // this will allow you to delete a truck object
    // This endpoint works 1/4/21 8:21pm
    @DeleteMapping("/truck/{truckid}")
    public ResponseEntity<?> deleteTruckById(
        @PathVariable
            long truckid)
    {
        truckservice.delete(truckid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
