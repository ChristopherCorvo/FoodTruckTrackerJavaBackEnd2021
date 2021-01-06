package com.lambdaschool.foundation.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "trucks")
public class Truck
{
    // ------------ Table Fields ---------------------
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long truckid; // primary key

//    @Column(unique = true,
//    nullable = false)
    private String name;

    private String imageOfTruck;

    private String cuisineType;

    private String truckLongitude;

    private String truckLatitude;

    private String departureTime; // need to change type 1/1/21 12:54pm

    private double customerRatingAvg;

    // ------------ Association Fields ---------------
    @OneToMany(mappedBy = "truck",
        cascade = CascadeType.ALL,
        orphanRemoval = true)
    @JsonIgnoreProperties(value = "truck",
        allowSetters = true)
    private List<MenuItem> menuItems = new ArrayList<>();

    @OneToMany(mappedBy = "truck",
    cascade = CascadeType.ALL,
    orphanRemoval = true)
    @JsonIgnoreProperties(value = "truck",
    allowSetters = true)
    private List<MyTruckList> mytrucklist = new ArrayList<>();

    // ------------ Constructors ---------------------
    public Truck()
    {
        // default constructor for JPA
    }

    // Constructor with parameters
    public Truck(
        String name,
        String imageOfTruck,
        String cuisineType,
        String truckLongitude,
        String truckLatitude,
        String departureTime,
        double customerRatingAvg)
    {
        this.name = name;
        this.imageOfTruck = imageOfTruck;
        this.cuisineType = cuisineType;
        this.truckLongitude = truckLongitude;
        this.truckLatitude = truckLatitude;
        this.departureTime = departureTime;
        this.customerRatingAvg = customerRatingAvg;
    }

    // ------------ Getters and Setters --------------
    public long getTruckid()
    {
        return truckid;
    }

    public void setTruckid(long truckId)
    {
        this.truckid = truckId;
    }

    // The below might need to be removed 1/1/2021 7:15pm
    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getImageOfTruck()
    {
        return imageOfTruck;
    }

    public void setImageOfTruck(String imageOfTruck)
    {
        this.imageOfTruck = imageOfTruck;
    }

    public String getCuisineType()
    {
        return cuisineType;
    }

    public void setCuisineType(String cuisineType)
    {
        this.cuisineType = cuisineType;
    }

    public String getTruckLongitude()
    {
        return truckLongitude;
    }

    public void setTruckLongitude(String truckLocation)
    {
        this.truckLongitude = truckLocation;
    }

    public String getTruckLatitude()
    {
        return truckLatitude;
    }

    public void setTruckLatitude(String truckLatitude)
    {
        this.truckLatitude = truckLatitude;
    }

    public String getDepartureTime()
    {
        return departureTime;
    }

    public void setDepartureTime(String departureTime)
    {
        this.departureTime = departureTime;
    }

    public double getCustomerRatingAvg()
    {
        return customerRatingAvg;
    }

    public void setCustomerRatingAvg(double customerRatingAvg)
    {
        this.customerRatingAvg = customerRatingAvg;
    }

    // ------- Association Field Getters and Setters -----
    public List<MenuItem> getMenuItems()
    {
        return menuItems;
    }

    public void setMenuItems(List<MenuItem> menuItems)
    {
        this.menuItems = menuItems;
    }

    public List<MyTruckList> getMytrucklist()
    {
        return mytrucklist;
    }

    public void setMytrucklist(List<MyTruckList> mytrucklist)
    {
        this.mytrucklist = mytrucklist;
    }
}
