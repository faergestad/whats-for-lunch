package com.itera.faergestad.whatsforlunch.api.domain;

public class Dish {

    private String name;

    public Dish(String dish) {
        this.name = dish;
    }

    public Dish() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
