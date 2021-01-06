package com.lambdaschool.foundation.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

/**
 * The entity allowing interaction with the roles table.
 */
@Entity
@Table(name = "roles")
public class Role
    extends Auditable
{
    // ----------- Table Fields ----------
    /**
     * The primary key (long) of the roles table.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long roleid; // primary key

    /**
     * The name (String) of the role. Cannot be null and must be unique.
     */
    // This might need to be changed 1/1/2021 12:41pm
    @NotNull
    @Column(unique = true)
    private String name; // name of given role

    // ------- Association Fields -----------
    /**
     * Part of the join relationship between user and role
     * connects roles to the user role combination
     */
    @OneToMany(mappedBy = "role",
        cascade = CascadeType.ALL,
        orphanRemoval = true)
    @JsonIgnoreProperties(value = "role",
        allowSetters = true)
    private Set<UserRoles> users = new HashSet<>();

    // --------------- Constructors ------------
    public Role()
    {
        //Default Constructor used primarily by the JPA.
    }

    /**
     * Given the name, create a new role object. User gets added later
     *
     * @param name the name of the role in uppercase
     */
    // Constructor with parameters
    public Role(String name)
    {
        this.name = name.toUpperCase();
    }

    // ------------- Getters and Setters -------------
    public long getRoleid()
    {
        return roleid;
    }

    /**
     * Setter for role id, used for seeding data
     *
     * @param roleid the new role id, primary key, (long) for this role
     */
    public void setRoleid(long roleid)
    {
        this.roleid = roleid;
    }

    /**
     * Getter for role name
     *
     * @return role name (String) in uppercase
     */
    public String getName()
    {
        return name;
    }

    /**
     * Setter for role name
     *
     * @param name the new role name (String) for this role, in uppercase
     */
    public void setName(String name)
    {
        this.name = name.toUpperCase();
    }

    /**
     * Getter for user role combinations
     *
     * @return A list of user role combinations associated with this role
     */
    public Set<UserRoles> getUsers()
    {
        return users;
    }

    /**
     * Setter for user role combinations
     *
     * @param users Change the list of user role combinations associated with this role to this one
     */
    public void setUsers(Set<UserRoles> users)
    {
        this.users = users;
    }
}
