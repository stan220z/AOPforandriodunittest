package cky.AspectJ;

import android.os.Environment;
import android.util.Log;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.concurrent.TimeUnit;

/**
 * Created by Asus on 2017/5/23.
 */

@Aspect
public class Function_info {

    private static final String TAG = "Function_info";
    private static String mFileDir = Environment.getExternalStorageDirectory()+
            "/cky/MyApplication/Function_info/";
    private static final String functionnamefile = "functionname.txt";
    private static final String functionargsfile = "functionargs.txt";
    private static final String functionruntime = "functionruntime.txt";
    private BufferedWriter out = null;
    public static int fheight = 0;

    //    @Around("execution(* *..app..*(..))")
    @Around("execution(* com.example.asus.myapplication..*(..))")
    public Object functionsAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        String function_name = proceedingJoinPoint.getSignature().toString();
        Object[] function_args = proceedingJoinPoint.getArgs();
        //控制缩进,表示函数调用关系
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < fheight; i++) {
            builder.append("  ");
        }
        //写函数的声明形式
        writefileadd(functionnamefile, builder.toString() + function_name);
        Log.d(TAG, "functionname: " + builder.toString() + function_name);
        //写函数传递的参数，类型与值
        String fargs=builder.toString();
        for(Object temp:function_args){
            if(temp==null){
                fargs+="复杂（不确定）类型"+":"+"null  ";
            }
            else {
                fargs+=temp.getClass().getName()+":"+temp.toString()+"  ";
            }
        }
        writefileadd(functionargsfile, fargs);
        Log.d(TAG, "functionargs: " + fargs);
        fheight++;
        long startNanos = System.nanoTime();
        Object result = proceedingJoinPoint.proceed();
        long stopNanos = System.nanoTime();
        fheight--;
        writefileadd(functionruntime, builder.toString() + TimeUnit.NANOSECONDS.toMillis(startNanos) +
                "," + TimeUnit.NANOSECONDS.toMillis(stopNanos) + "," + TimeUnit.NANOSECONDS.toMillis(stopNanos - startNanos));
        Log.d(TAG, "functionsruntime: " + builder.toString() + TimeUnit.NANOSECONDS.toMillis(startNanos) +
                "," + TimeUnit.NANOSECONDS.toMillis(stopNanos) + "," + TimeUnit.NANOSECONDS.toMillis(stopNanos - startNanos));
        return result;
    }

    public static void writefileadd(String fileName, String content) {
        try {
            File file = new File(mFileDir+fileName);
            if (!file.exists()) {
                Log.d("TestFile", "Create the file:" + mFileDir);
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            content+="\r\n";
            RandomAccessFile raf = new RandomAccessFile(file, "rwd");
            raf.seek(file.length());
            raf.write(content.getBytes());
            raf.close();
        } catch (IOException e) {
            Log.d("Exception", e.toString());

        }
    }
}