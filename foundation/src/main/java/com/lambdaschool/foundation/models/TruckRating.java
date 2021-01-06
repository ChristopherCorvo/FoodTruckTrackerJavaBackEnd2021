package com.lambdaschool.foundation.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "truckratings")
public class TruckRating
{
    // ------------ Table Fields ---------------------
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long truckratingid; // primary key

    private Integer rating;
    // ------------ Association Fields ---------------

    // Many to One relationship w/ user
    @ManyToOne
    @JoinColumn(name = "userid",
        nullable = false)
    @JsonIgnoreProperties(value = "truckrating",
        allowSetters = true)
    private User user;

    // Many to One relationship w/ truck
    @ManyToOne
    @JoinColumn(name = "truckid",
        nullable = false)
    @JsonIgnoreProperties(value = "truckrating",
        allowSetters = true)
    private Truck truck;

    // ------------ Constructors ---------------------
    public TruckRating()
    {
        // default constructor for JPA
    }

    // Constructor with parameters
    public TruckRating(Integer rating)
    {
        this.rating = rating;
    }

    // ------------ Getters and Setters --------------
    public long getTruckratingid()
    {
        return truckratingid;
    }

    public void setTruckratingid(long truckratingid)
    {
        this.truckratingid = truckratingid;
    }

    public Integer getRating()
    {
        return rating;
    }

    public void setRating(Integer rating)
    {
        this.rating = rating;
    }

    // ---------- Association Fields Getters and Setters ------

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }

    public Truck getTruck()
    {
        return truck;
    }

    public void setTruck(Truck truck)
    {
        this.truck = truck;
    }
}
