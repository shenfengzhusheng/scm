package org.xfs.core.platform.aop;

import java.util.Map;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
// without this shit will not work
public class AopConfig {


    private ThreadLocal<StopWatch> sw = new ThreadLocal<StopWatch>();

    @Pointcut("execution(* ck.org.xfs.core.business..web.*Web.*(..))")
    // @Pointcut("execution(* org.xfs.core.business.test.web.TestWeb.test(..))")
    private void point() {};

    @Before("point() && args(name) ")
    public void doAccessCheck(String name) {
        System.out.println(name);
        // System.out.println("前置通知==============");
    }

    @Before("point()")
    public void doBefore() {
        StopWatch watch = new StopWatch();
        watch.start(Thread.currentThread().getName() + "方法监迭");
        sw.set(watch);
        System.out.println("前置通知==============");

    }

    @AfterReturning(pointcut = "point()", returning = "returnValue")
    public void doAfter(JoinPoint point, Object returnValue) {
        StopWatch watch = sw.get();
        watch.stop();
        // System.out.println("@AfterReturning：参数为：" +Arrays.toString(point.getArgs()));
        // Gson gson=new Gson();
        // System.out.println("@AfterReturning：返回值为：" +gson.toJson(returnValue));

        for (Map.Entry<Thread, StackTraceElement[]> entry : Thread.getAllStackTraces().entrySet()) {
            for (StackTraceElement ste : entry.getValue()) {
                if (ste.getClassName().contains("org.xfs.core")) {
                    // System.out.println("fileName：" + ste.getFileName());
                    // System.out.println("methodName：" + ste.getMethodName());

                    // System.out.println("后置通知调用情况：" + ste.getClassName());

                    // System.out.println("后置通知调用情况：" + ste);
                }
            }
        }
        if (Thread.currentThread().getId() > 300) {

            System.out.println("后置通知耗时" + point.getTarget().getClass().getName());

            System.out.println(watch.getLastTaskInfo().getTaskName() + "后置通知耗时" + watch.getTotalTimeMillis());

        }
    }


    @After("point()")
    public void after() {
        System.out.println("最终通知---------------");
    }

    @AfterThrowing("point()")
    public void doAfterThrow() {
        System.out.println("异常通知");
    }

    @SuppressWarnings("rawtypes")
    @Around("point()")
    public Object doBasicProfiling(ProceedingJoinPoint pjp) throws Throwable {
        StopWatch watch = new StopWatch();
        watch.start("方法");
        // System.out.println("进入环绕通知");
        Object object = pjp.proceed();// 执行该方法
        @SuppressWarnings("unused")
        Class clazz = pjp.getTarget().getClass();
        // System.out.println(clazz.getName()+"环绕入参："+new Gson().toJson(pjp.getArgs()));

        // System.out.println("环绕返回："+new Gson().toJson(object));
        watch.stop();
        // System.out.println(watch.prettyPrint());

        return object;
    }


}
