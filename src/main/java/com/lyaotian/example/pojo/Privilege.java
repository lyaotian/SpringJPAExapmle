package com.lyaotian.example.pojo;

import javax.persistence.*;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * User: Yaotian Leung
 * Date: 7/29/13
 * Time: 9:12 PM
 */
@Entity
public class Privilege {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;
    private String name;

    @ManyToMany(mappedBy = "privileges")
    private Set<Role> roles = new LinkedHashSet<Role>();

    public Privilege(){

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
