package io.educative.utils.aop;

import org.slf4j.Logger;

public interface AuditLoggable<T> {

    public void auditLog(T t, String eventName);

}