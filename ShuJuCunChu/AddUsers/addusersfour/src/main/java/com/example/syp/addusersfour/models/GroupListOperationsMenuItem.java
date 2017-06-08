package com.example.syp.addusersfour.models;

/**
 * Created by syp on 17-6-8.
 */

public class GroupListOperationsMenuItem {
    public static final int OPERATION_DELETE = 1;
    public static final int OPERATION_EDIT = 2;

    private int operation;
    private String name;

    public GroupListOperationsMenuItem(int operation, String name) {
        this.operation = operation;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getOperation() {
        return operation;
    }

    @Override
    public String toString() {
        return getName();
    }
}
