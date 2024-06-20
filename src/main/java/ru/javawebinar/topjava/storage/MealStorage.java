package ru.javawebinar.topjava.storage;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class MealStorage implements Storage<Meal> {
    private final Map<Integer, Meal> mealStorage = new ConcurrentHashMap<>();
    private final AtomicInteger mealId = new AtomicInteger(0);

    {
        fillStorage();
    }

    private void fillStorage() {
        add(new Meal(LocalDateTime.of(2024, Month.JUNE, 1, 8, 0), "Завтрак", 1000));
        add(new Meal(LocalDateTime.of(2024, Month.JUNE, 1, 14, 0), "Обед", 1100));
        add(new Meal(LocalDateTime.of(2024, Month.JUNE, 1, 17, 59), "Ужин", 900));
        add(new Meal(LocalDateTime.of(2024, Month.JUNE, 2, 8, 0), "Завтрак", 1000));
        add(new Meal(LocalDateTime.of(2024, Month.JUNE, 2, 14, 0), "Обед", 1100));
        add(new Meal(LocalDateTime.of(2024, Month.JUNE, 2, 17, 59), "Ужин", 900));
        add(new Meal(LocalDateTime.of(2024, Month.JUNE, 2, 21, 00), "Ночной дожор", 500));
        add(new Meal(LocalDateTime.of(2024, Month.JUNE, 3, 8, 0), "Завтрак", 1000));
        add(new Meal(LocalDateTime.of(2024, Month.JUNE, 3, 14, 0), "Обед", 1000));
        add(new Meal(LocalDateTime.of(2024, Month.JUNE, 3, 17, 59), "Ужин", 900));
    }

    @Override
    public Meal add(Meal meal) {
        int key = nextId();
        meal.setId(key);
        mealStorage.put(key, meal);
        return meal;
    }

    private int nextId() {
        return mealId.incrementAndGet();
    }

    @Override
    public void delete(int id) {
        mealStorage.remove(id);
    }

    @Override
    public Meal update(Meal meal) {
        return mealStorage.replace(meal.getId(), meal) != null ? meal : null;
    }

    @Override
    public List<Meal> getAll() {
        return new ArrayList<>(mealStorage.values());
    }

    @Override
    public Meal getById(int id) {
        return mealStorage.get(id);
    }

}