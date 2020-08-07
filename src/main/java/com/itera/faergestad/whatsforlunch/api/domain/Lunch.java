package com.itera.faergestad.whatsforlunch.api.domain;

import java.util.List;

public class Lunch {

    private List<Dish> dishes;

    public Lunch(List<Dish> dishes) {
        this.dishes = dishes;
    }

    public Lunch() {
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }
}
