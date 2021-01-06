package com.lambdaschool.foundation.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "menuitems")
public class MenuItem
{

    // ------------ Table Fields ---------------------
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long menuitemid; // primary key

    @Column(nullable = false)
    private String itemName;

    private String itemDescription;

    private double itemPrice;

    private double customerRatingAvg;

    // ------------ Association Fields ---------------
    // Many to One relationship w/ truck
    @ManyToOne
    @JoinColumn(name = "truckid",
        nullable = false)
    @JsonIgnoreProperties(value = "menuItem",
        allowSetters = true)
    private Truck truck;

    // One to Many relationship w/ menuItemPhotos
    @OneToMany(mappedBy = "menuitem",
        cascade = CascadeType.ALL,
        orphanRemoval = true)
    @JsonIgnoreProperties(value = "menuitem",
        allowSetters = true)
    private List<MenuItemPhoto> menuItemPhotos = new ArrayList<>();

    // One to Many relationship w/ menuItemRatings
    @OneToMany(mappedBy = "menuitem",
        cascade = CascadeType.ALL,
        orphanRemoval = true)
    @JsonIgnoreProperties(value = "menuitem",
        allowSetters = true)
    private List<MenuItemRating> menuitemratings = new ArrayList<>();

    // ------------ Constructors ---------------------
    public MenuItem()
    {
        // default constructor for JPA
    }
    // Constructor with parameters
    public MenuItem(
        String itemName,
        String itemDescription,
        double itemPrice,
        double customerRatingAvg,
        Truck truck)
    {
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.itemPrice = itemPrice;
        this.customerRatingAvg = customerRatingAvg;
        this.truck = truck;
    }

    // ------------ Getters and Setters --------------
    public long getMenuitemid()
    {
        return menuitemid;
    }

    public void setMenuitemid(long menuitemsid)
    {
        this.menuitemid = menuitemsid;
    }

    public String getItemName()
    {
        return itemName;
    }

    public void setItemName(String itemName)
    {
        this.itemName = itemName;
    }

    public String getItemDescription()
    {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription)
    {
        this.itemDescription = itemDescription;
    }

    public double getItemPrice()
    {
        return itemPrice;
    }

    public void setItemPrice(double itemPrice)
    {
        this.itemPrice = itemPrice;
    }

    public double getCustomerRatingAvg()
    {
        return customerRatingAvg;
    }

    public void setCustomerRatingAvg(double customerRatingAvg)
    {
        this.customerRatingAvg = customerRatingAvg;
    }

    // ------ Association Fields Getters and Setters -------

    public Truck getTruck()
    {
        return truck;
    }

    public void setTruck(Truck truck)
    {
        this.truck = truck;
    }

    public List<MenuItemPhoto> getMenuItemPhotos()
    {
        return menuItemPhotos;
    }

    public void setMenuItemPhotos(List<MenuItemPhoto> menuItemPhotos)
    {
        this.menuItemPhotos = menuItemPhotos;
    }

    public List<MenuItemRating> getMenuitemratings()
    {
        return menuitemratings;
    }

    public void setMenuitemratings(List<MenuItemRating> menuitemratings)
    {
        this.menuitemratings = menuitemratings;
    }


}
