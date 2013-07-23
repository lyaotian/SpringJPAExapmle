package com.lyaotian.example.pojo;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * User: Yaotian Leung
 * Date: 7/22/13
 * Time: 10:01 PM
 */
public class SystemUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private String md5Hex;

    public SystemUser(){

    }

    public SystemUser(String name, String md5Hex) {
        this.name = name;
        this.md5Hex = md5Hex;
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

    public String getMd5Hex() {
        return md5Hex;
    }

    public void setMd5Hex(String md5Hex) {
        this.md5Hex = md5Hex;
    }
}
