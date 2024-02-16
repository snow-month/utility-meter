package ru.homelab.aspect.timer;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class TimerAspect {
    @Around("execution(* *(..)) && @annotation(ru.homelab.aspect.timer.Timer)")
    public Object around(ProceedingJoinPoint point) {
        long startTime = System.nanoTime();
        try {
            return point.proceed();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        } finally {
            long endTime = System.nanoTime();
            long time = (endTime - startTime) / 1000000;
            System.out.println(point + " -> " + time + " ms");
        }
    }
}
