package com.xuzhihui.picsart.ui;

import android.util.Log;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

/**
 * Created by Asus on 2017/5/24.
 */

@Aspect
public class AspectTest {

    private static final String TAG = "xuyisheng";

    @Before("execution(* com.xuzhihui.picsart.ui.*.*(..))")
    public void onActivityMethodBefore(JoinPoint joinPoint) throws Throwable {
        String key = joinPoint.getSignature().toString();
        Log.d(TAG, "onActivityMethodBefore: " + key);
    }
}