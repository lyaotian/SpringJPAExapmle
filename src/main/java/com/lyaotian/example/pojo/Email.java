package com.lyaotian.example.pojo;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * User: Yaotian Leung
 * Date: 7/19/13
 * Time: 10:51 PM
 */
@Entity
public class Email {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;
    private String email;

    public Email(){

    }

    public Email(String email) {
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Email email1 = (Email) o;

        if (id != email1.id) return false;
        if (!email.equals(email1.email)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + email.hashCode();
        return result;
    }
}
