package io.educative.utils.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclareParents;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class AuditLoggingAspect {

    @DeclareParents(value = "io.educative.services.*", defaultImpl = AuditLoggableImpl.class)
    public static AuditLoggable auditLoggable;

}
