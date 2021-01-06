package com.lambdaschool.foundation.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "menuitemphotos")
public class MenuItemPhoto
{
    // ------------ Table Fields ---------------------
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long menuitemphotoid; //primary key

    private String photoURL;
    // ------------ Association Fields ---------------

    // Many to One relationship w/ menuItems

    @ManyToOne
    @JoinColumn(name = "menuitemid",
        nullable = false)
    @JsonIgnoreProperties(value = "menuItemPhotos",
        allowSetters = true)
    private MenuItem menuitem;
    // ------------ Constructors ---------------------
    public MenuItemPhoto()
    {
        // default constructor for JPA
    }

    // Constructor with parameters
    public MenuItemPhoto(
        String photoURL,
        MenuItem menuitem)
    {
        this.photoURL = photoURL;
        this.menuitem = menuitem;
    }

    // ------------ Getters and Setters --------------
    public long getMenuitemphotoid()
    {
        return menuitemphotoid;
    }

    public void setMenuitemphotoid(long menuitemphotoid)
    {
        this.menuitemphotoid = menuitemphotoid;
    }

    public String getPhotoURL()
    {
        return photoURL;
    }

    public void setPhotoURL(String photoURL)
    {
        this.photoURL = photoURL;
    }

    // ------ Association Fields Getters and Setters ----------
    public MenuItem getMenuitem()
    {
        return menuitem;
    }

    public void setMenuitem(MenuItem menuitem)
    {
        this.menuitem = menuitem;
    }
}
