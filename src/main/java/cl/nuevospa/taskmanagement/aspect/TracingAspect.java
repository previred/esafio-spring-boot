package cl.nuevospa.taskmanagement.aspect;

import cl.nuevospa.taskmanagement.util.JsonUtils;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


@Log4j2
@Aspect
@Component
public class TracingAspect {

    @Around("@annotation(cl.nuevospa.taskmanagement.annotation.Trace)")
    public Object aroundAdvice(ProceedingJoinPoint joinPoint) throws Throwable {

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        log.info(" ");
        log.info("***************************************************************************************************************************");
        log.info("INIT: " + joinPoint.getTarget().getClass().getSimpleName() + "::" + method.getName());
        log.info(joinPoint.getTarget().getClass().getSimpleName() + "::" + method.getName() + "::Parameters: " + JsonUtils.sampleObjectToJSONString(parametersNamesValuesToMap(signature.getParameterNames(), joinPoint.getArgs())));

        Object proceed = joinPoint.proceed();
        log.info(joinPoint.getTarget().getClass().getSimpleName() + "::" + method.getName() + "::RESULT: " + JsonUtils.sampleObjectToJSONString(proceed));

        log.info("END: " + joinPoint.getTarget().getClass().getSimpleName() + "::" + method.getName());

        return proceed;
    }

    private Map<String, Object> parametersNamesValuesToMap(String[] parameterNames, Object[] args) {
        Map<String, Object> result = new HashMap<>();
        if (Objects.nonNull(parameterNames) && parameterNames.length > 0) {
            for (int i = 0; i < parameterNames.length; i++) {
                if (!parameterNames[i].equalsIgnoreCase("bindingResult")) {
                    result.put(parameterNames[i], args[i]);
                }
            }
        }
        return result;
    }
}