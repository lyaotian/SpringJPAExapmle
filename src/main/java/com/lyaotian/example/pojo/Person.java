package com.lyaotian.example.pojo;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * User: Yaotian Leung
 * Date: 7/19/13
 * Time: 10:49 PM
 */
@Entity
public class Person {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String lastName;
    private boolean isFemale;
    @Temporal(TemporalType.TIMESTAMP)
    private Date birthday;
    @ManyToMany
    private Set<Email> emails = new HashSet<Email>();

    public Person(){

    }

    public Person(String firstName, String lastName, boolean female, Date birthday) {
        this.firstName = firstName;
        this.lastName = lastName;
        isFemale = female;
        this.birthday = birthday;
    }

    public long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean isFemale() {
        return isFemale;
    }

    public void setFemale(boolean female) {
        isFemale = female;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Set<Email> getEmails() {
        return emails;
    }

    public void setEmails(Set<Email> emails) {
        this.emails = emails;
    }
}
