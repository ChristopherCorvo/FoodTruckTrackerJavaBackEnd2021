package com.lambdaschool.foundation.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "mytrucklist")
@IdClass(MyTruckListId.class)
public class MyTruckList
    extends Auditable
    implements Serializable
{
    // -------- Association Fields ------------
    /**
     * 1/2 of the primary key (long) for userroles.
     * Also is a foreign key into the users table
     */
    @Id
    @ManyToOne
    @NotNull
    @JoinColumn(name = "userid")
    @JsonIgnoreProperties(value = "truck",
        allowSetters = true)
    private User user;

    /**
     * 1/2 of the primary key (long) for userroles.
     * Also is a foreign key into the roles table
     */
    @Id
    @ManyToOne
    @NotNull
    @JoinColumn(name = "truckid")
    @JsonIgnoreProperties(value = "user",
        allowSetters = true)
    private Truck truck;

    // -------------- Constructors -----------------
    public MyTruckList()
    {
    }

    // Constructor with parameters
    public MyTruckList(
        @NotNull User user,
        @NotNull Truck truck)
    {
        this.user = user;
        this.truck = truck;
    }

    // -------------- Getters and Setters -----------
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

    // -------------- Getters and Setters -------------


    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (!(o instanceof MyTruckList))
        {
            return false;
        }
        MyTruckList that = (MyTruckList) o;
        return ((user == null) ? 0 : user.getUserid()) == ((that.user == null) ? 0 : that.user.getUserid()) &&
            ((truck == null) ? 0 : truck.getTruckid()) == ((that.truck == null) ? 0 : that.truck.getTruckid());
    }

    @Override
    public int hashCode()
    {
        // return Objects.hash(user.getUserid(), role.getRoleid());
        return 37;
    }
}