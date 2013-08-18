package com.lyaotian.example.pojo;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * User: Yaotian Leung
 * Date: 7/29/13
 * Time: 9:12 PM
 */
@Entity
public class Role {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;
    private String name;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "role_privilege",
            joinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "privilege_id", referencedColumnName = "id")}
    )
    @OrderBy("id ASC")
    private Set<Privilege> privileges = new LinkedHashSet<Privilege>();

    public Role(){

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

    public Set<Privilege> getPrivileges() {
        return privileges;
    }

    public void setPrivileges(Set<Privilege> privileges) {
        this.privileges = privileges;
    }
}
