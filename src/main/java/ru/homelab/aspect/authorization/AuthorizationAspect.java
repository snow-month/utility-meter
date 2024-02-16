package ru.homelab.aspect.authorization;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import ru.homelab.aspect.logging.Logging;
import ru.homelab.entity.User;

@Aspect
public class AuthorizationAspect {
    @Before("execution(* *(..)) && @annotation(ru.homelab.aspect.authorization.Authorization)")
    public void around(JoinPoint point) {
        MethodSignature methodSignature = (MethodSignature) point.getSignature();
        Logging annotation = methodSignature.getMethod().getAnnotation(Logging.class);

        Object[] args = point.getArgs();
        var request = (HttpServletRequest) args[0];
        var response = (HttpServletResponse) args[1];

        var user = (User) request.getSession().getAttribute("user");
//        return user != null;
        // todo проверка роли
//        return user != null && user.getRole() == Role.ADMIN;
    }
}
