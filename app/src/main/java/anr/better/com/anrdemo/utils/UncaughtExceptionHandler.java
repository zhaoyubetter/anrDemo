package anr.better.com.anrdemo.utils;

import android.content.Context;
import android.os.Build;
import android.util.Log;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 用于捕获未处理的异常信息
 *
 * @author zhaoyu1
 */
public class UncaughtExceptionHandler implements
        Thread.UncaughtExceptionHandler {
    private static final String TAG = "exceptionHandler";
    private Context context;

    public UncaughtExceptionHandler(Context ctx) {
        context = ctx;
    }

    public void uncaughtException(Thread thread, Throwable exception) {
        final StringWriter stackTrace = new StringWriter();
        exception.printStackTrace(new PrintWriter(stackTrace));
        Log.e(TAG, stackTrace.toString());
        saveErrorLog(stackTrace.toString());
    }

    private void saveErrorLog(String stackInfo) {
        StringBuilder reportText = new StringBuilder();
        reportText
                .append("DateTime:")
                .append(getFormatString(System.currentTimeMillis(), "yyyy-MM-dd hh:mm:ss")).append("\n");
        reportText.append("Model:").append(Build.MODEL).append("\n");
        reportText.append("Device:").append(Build.DEVICE).append("\n");
        reportText.append("Product:").append(Build.PRODUCT).append("\n");
        reportText.append("Manufacturer:").append(Build.MANUFACTURER)
                .append("\n");
        // 系统版本
        reportText.append("Version:").append(Build.VERSION.RELEASE)
                .append("\n");
        // 软件版本
        reportText.append("AppVersion:");
        reportText.append(stackInfo).append("\n");
        FileUtils.saveFile(reportText.toString(), context.getCacheDir(), "bugs.txt", true);
    }

    /**
     * 根据给定格式化时间值
     */
    public static String getFormatString(long timeMillis, String format) {
        DateFormat formater = new SimpleDateFormat(format);
        return formater.format(new Date(timeMillis));
    }

}
