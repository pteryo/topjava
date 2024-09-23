package ru.javawebinar.topjava.web.user;


import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import ru.javawebinar.topjava.HasEmail;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.UserRepository;
import ru.javawebinar.topjava.web.ExceptionInfoHandler;
import ru.javawebinar.topjava.web.SecurityUtil;

import javax.servlet.http.HttpServletRequest;

@Component
public class EmailChecker implements org.springframework.validation.Validator {

    private final UserRepository repository;
    private final HttpServletRequest request;

    public EmailChecker(UserRepository repository, @Nullable HttpServletRequest request) {
        this.repository = repository;
        this.request = request;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return HasEmail.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        HasEmail user = ((HasEmail) target);
        if (StringUtils.hasText(user.getEmail())) {
            User dbUser = repository.getByEmail(user.getEmail().toLowerCase());
            if (dbUser != null) {
                Assert.notNull(request, "HttpServletRequest missed");
                if (!(((request.getMethod().matches("PUT|POST") && user.getId() != null)) &&
                ((user.getId() != null && dbUser.id() == user.id()) || (checkUserIdNull(dbUser.id()))))) {
                    errors.rejectValue("email", ExceptionInfoHandler.EXCEPTION_DUPLICATE_EMAIL);
                }
            }
        }
    }
    private boolean checkUserIdNull(int idFromDb) {
        String requestURI = request.getRequestURI();
        return requestURI.endsWith("/" + idFromDb) || (idFromDb == SecurityUtil.get().getId() && requestURI.contains("/profile"));
    }
}