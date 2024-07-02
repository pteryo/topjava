package ru.javawebinar.topjava;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.web.SecurityUtil;
import ru.javawebinar.topjava.web.meal.MealRestController;
import ru.javawebinar.topjava.web.user.AdminRestController;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class SpringMain {
    public static void main(String[] args) {
        try (ConfigurableApplicationContext appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml")) {
            AdminRestController adminUserController = appCtx.getBean(AdminRestController.class);
            adminUserController.create(new User(null, "userName", "email@mail.ru", "password", Role.ADMIN));
            SecurityUtil.setAuthUserId(3);
            MealRestController mealRestController = appCtx.getBean(MealRestController.class);
            SecurityUtil.setAuthUserId(2);
            int firstMealId = 1;
            int secondMealId = 2;
            Meal t = mealRestController.update(new Meal(LocalDateTime.now(), "Еда 2 пользователя", 0), secondMealId);
            System.out.println("Вся еда");
            mealRestController.getAll().forEach(System.out::println);
            System.out.println("Только завтраки");
            mealRestController.getAllFiltered(null, LocalTime.of(10, 0), null, LocalTime.of(11, 0)).forEach(System.out::println);
        }
    }
}
