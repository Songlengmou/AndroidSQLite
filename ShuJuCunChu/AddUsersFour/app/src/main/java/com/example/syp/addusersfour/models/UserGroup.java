package com.example.syp.addusersfour.models;

/**
 * Created by syp on 17-6-9.
 */

public class UserGroup {
    private int id;
    private String name;

    public UserGroup(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return getName();
    }
}
