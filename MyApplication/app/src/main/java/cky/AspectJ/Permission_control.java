package cky.AspectJ;

import android.os.Environment;
import android.util.Log;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import static android.R.attr.key;

/**
 * Created by Asus on 2017/5/28.
 */
@Aspect
public class Permission_control {

    private static final String TAG = "Permission_control";
    private static String mFileDir = Environment.getExternalStorageDirectory()+
            "/cky/MyApplication/Permission_control/";

    @Before("call(* *..getSystemService(..))")
    public Object functionsBefore(JoinPoint joinPoint) throws Throwable {

        String function_name = joinPoint.getSignature().toString();
        Log.d(TAG, "权限: " + key);


    }
