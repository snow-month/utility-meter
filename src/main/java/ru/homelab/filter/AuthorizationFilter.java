package ru.homelab.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.homelab.entity.User;

import java.io.IOException;
import java.util.Set;

import static ru.homelab.util.UrlPath.LOGIN;
import static ru.homelab.util.UrlPath.REGISTRATION;

//@WebFilter("/*")
public class AuthorizationFilter implements Filter {
    private static final Set<String> PUBLIC_PATH = Set.of(LOGIN, REGISTRATION);

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String uri = ((HttpServletRequest) servletRequest).getRequestURI();
        if (isPublicPath(uri) || isLoggedIn(servletRequest)) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            ((HttpServletResponse) servletResponse).setStatus(HttpServletResponse.SC_FORBIDDEN);
        }
    }

    private boolean isPublicPath(String uri) {
        return PUBLIC_PATH.stream().anyMatch(path -> path.startsWith(uri));
    }

    private boolean isLoggedIn(ServletRequest servletRequest) {
        var user = (User) ((HttpServletRequest) servletRequest).getSession().getAttribute("user");
        return user != null;
        // todo проверка роли
//        return user != null && user.getRole() == Role.ADMIN;
    }
}
