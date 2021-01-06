package com.lambdaschool.foundation.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "menuitemratings")
public class MenuItemRating
{
    // ------------ Table Fields ---------------------
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long menuitemratingid; // primary key

    private Integer itemRating;

    // ------------ Association Fields ---------------
    // Many to One relationship w/ menuItems
    @ManyToOne
    @JoinColumn(name = "menuitemid",
        nullable = false)
    @JsonIgnoreProperties(value = "menuitemrating",
        allowSetters = true)
    private MenuItem menuitem;

    // Many to One relationship w/ users
//    @ManyToOne
//    @JoinColumn(name = "userid",
//        nullable = false)
//    @JsonIgnoreProperties(value = "menuitemrating",
//        allowSetters = true)
//    private User user;

    // ------------ Constructors ---------------------

    public MenuItemRating()
    {
        // default constructor for JPA
    }

    // Constructor with parameters


    public MenuItemRating(
        Integer itemRating,
        MenuItem menuitem)
    {
        this.itemRating = itemRating;
        this.menuitem = menuitem;
    }

    // ------------ Getters and Setters --------------
    public long getMenuitemratingid()
    {
        return menuitemratingid;
    }

    public void setMenuitemratingid(long menuitemratingid)
    {
        this.menuitemratingid = menuitemratingid;
    }

    public Integer getItemRating()
    {
        return itemRating;
    }

    public void setItemRating(Integer itemRating)
    {
        this.itemRating = itemRating;
    }
    // ------- Association Fields Getters and Setters -------

    public MenuItem getMenuitem()
    {
        return menuitem;
    }

    public void setMenuitem(MenuItem menuitem)
    {
        this.menuitem = menuitem;
    }

//    public User getUser()
//    {
//        return user;
//    }
//
//    public void setUser(User user)
//    {
//        this.user = user;
//    }
}
