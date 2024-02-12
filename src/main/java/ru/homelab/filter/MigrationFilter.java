package ru.homelab.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import ru.homelab.service.impl.MigrationService;
import ru.homelab.service.impl.PropertiesService;

import java.io.IOException;

@WebFilter("/*")
public class MigrationFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        try {
            Class.forName(PropertiesService.get("db.driver"));
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("sql exception, load driver Postgresql: " + e.getMessage());
        }

        new MigrationService().init(
                PropertiesService.get("db.url"),
                PropertiesService.get("db.username"),
                PropertiesService.get("db.password"),
                "db/changelog/changelog.xml",
                PropertiesService.get("liquibase.defaultSchemaName")
        );
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        filterChain.doFilter(servletRequest, servletResponse);
    }

}
