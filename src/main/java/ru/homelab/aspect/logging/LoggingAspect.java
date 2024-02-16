package ru.homelab.aspect.logging;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import ru.homelab.repository.AuditRepository;
import ru.homelab.repository.impl.AuditRepositoryImpl;
import ru.homelab.service.AuditService;
import ru.homelab.service.DBConnectionProvider;
import ru.homelab.service.impl.AuditServiceImpl;
import ru.homelab.service.impl.DBConnectionProviderImpl;

@Aspect
public class LoggingAspect {
    private final DBConnectionProvider provider = new DBConnectionProviderImpl();
    private final AuditRepository repository = new AuditRepositoryImpl(provider);
    private final AuditService service = new AuditServiceImpl(repository);

    @Before("execution(* *(..)) && @annotation(ru.homelab.aspect.logging.Logging)")
    public void around(JoinPoint point) {
        MethodSignature methodSignature = (MethodSignature) point.getSignature();
        Logging annotation = methodSignature.getMethod().getAnnotation(Logging.class);
        String message = annotation.message();

        service.save(message);
    }
}
