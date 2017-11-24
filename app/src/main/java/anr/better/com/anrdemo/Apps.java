package anr.better.com.anrdemo;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;


import com.github.anrwatchdog.ANRWatchDog;

import java.util.Iterator;
import java.util.List;

import anr.better.com.anrdemo.utils.UncaughtExceptionHandler;


/**
 * Created by zhaoyu1 on 2017/11/13.
 */

public class Apps extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Thread.setDefaultUncaughtExceptionHandler(new UncaughtExceptionHandler(this));
        new ANRWatchDog().start();
    }


    protected ActivityManager.ProcessErrorStateInfo a(Context var1, long var2) {
        var2 = var2 < 0L ? 0L : var2;
        ActivityManager var4 = (ActivityManager) var1.getSystemService(Context.ACTIVITY_SERVICE);
        long var5 = var2 / 500L;
        int var7 = 0;
        do {
            List var8 = var4.getProcessesInErrorState();
            if (var8 != null) {
                Iterator var9 = var8.iterator();
                while (var9.hasNext()) {
                    ActivityManager.ProcessErrorStateInfo var10 = (ActivityManager.ProcessErrorStateInfo) var9.next();
                    if (var10.condition == 2) {
                        return var10;
                    }
                }
            }
        } while ((long) (var7++) < var5);
        return null;
    }
}
