package ru.javawebinar.topjava.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Meal {
    private Integer id;

    private final LocalDateTime dateTime;

    private final String description;

    private final int calories;

    public Meal(LocalDateTime ldt, String description, int calories) {
        this(null, ldt, description, calories);
    }

    public Meal(Integer id, LocalDateTime ldt, String description, int calories) {
        this.id = id;
        this.dateTime = ldt;
        this.description = description;
        this.calories = calories;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getDescription() {
        return description;
    }

    public int getCalories() {
        return calories;
    }

    public LocalDate getDate() {
        return dateTime.toLocalDate();
    }

    public LocalTime getTime() {
        return dateTime.toLocalTime();
    }
}