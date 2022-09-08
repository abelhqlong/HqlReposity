package com.gremlin.aspect;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gremlin.annotation.CustomLog;
import com.gremlin.utils.JsonFormatTool;
import com.gremlin.utils.XmlUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;
import java.util.UUID;

/**
 * @className: LogAspect
 * @author: gremlin
 * @version: 1.0.0
 * @description:
 * @date: 2022/8/14 23:07
 */
@Slf4j
@Aspect
@Component
public class LogAspect {

    @Autowired
    private ObjectMapper objectMapper;

    @Around(value = "@annotation(customLog)")
    public Object around(ProceedingJoinPoint point, CustomLog customLog) throws Throwable {
        HttpServletRequest request =
                ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();

        // 开始时间
        long startTime = System.currentTimeMillis();
        UUID uuid = UUID.randomUUID();
        // 获取CustomLog注解参数值
        String notes = customLog.notes();
        // 获取请求地址
        String reqPath = request.getServletPath();
        // 方法参数
        Object[] args = point.getArgs();
        Object o = args[0];
        String str = XmlUtil.convertToXml(o);
        log.info("CustomLog : {},请求接口地址 : {},接口用途 : {},请求数据...{}",
                uuid,reqPath,notes,str);

        // 获取返回值
        Object proceed = point.proceed();
        // 将返回值转换为json格式
        String jsonData = objectMapper.writeValueAsString(proceed);
        // 结束时间
        long endTime = System.currentTimeMillis();
        // 日志打印
        log.info("CustomLog : {},请求接口地址 : {},接口用途 : {},方法耗时 : {},返回数据JSON格式...{}",
                uuid,reqPath,notes, endTime-startTime, JsonFormatTool.formatJson(jsonData));

        return proceed;
    }
}
