package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static ru.javawebinar.topjava.util.ValidationUtil.assureIdConsistent;
import static ru.javawebinar.topjava.util.ValidationUtil.checkNew;
import static ru.javawebinar.topjava.web.SecurityUtil.authUserCaloriesPerDay;
import static ru.javawebinar.topjava.web.SecurityUtil.authUserId;

@Controller
public class MealRestController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private MealService service;

    public List<MealTo> getAll() {
        int authUserId = authUserId();
        log.info("getAll for userId {}", authUserId);
        return  MealsUtil.getTos(service.getAll(authUserId), authUserCaloriesPerDay());
    }

    public List<MealTo> getAllFiltered(LocalDate startDate, LocalTime startTime, LocalDate endDate, LocalTime endTime) {
        int authUserId = authUserId();
        log.info("getFiltered for userId {} from {} to {}", authUserId, startDate, endDate);
        return MealsUtil.getTosFiltered(service.getFiltered(authUserId, startDate, endDate), authUserCaloriesPerDay(), startTime, endTime);
    }

    public Meal get(int id) {
        int authUserId = authUserId();
        log.info("get {} for userId {}", id, authUserId);
        return service.get(id, authUserId);
    }

    public Meal create(Meal meal) {
        int authUserId = authUserId();
        log.info("create {} for userId {}", meal, authUserId);
        checkNew(meal);
        return service.create(meal, authUserId);
    }

    public void delete(int id) {
        int authUserId = authUserId();
        log.info("delete {} for userId {}", id, authUserId);
        service.delete(id, authUserId);
    }

    public Meal update(Meal meal, int id) {
        int authUserId = authUserId();
        log.info("update {} with id={} for userId {}", meal, id, authUserId);
        assureIdConsistent(meal, id);
        return service.update(meal, authUserId);
    }
}