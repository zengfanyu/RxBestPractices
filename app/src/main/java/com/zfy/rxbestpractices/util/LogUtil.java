package com.zfy.rxbestpractices.util;

import android.text.TextUtils;
import android.util.Log;

import com.zfy.rxbestpractices.config.Constants;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 日志工具
 *
 * @author: fanyuzeng on 2018/3/1 10:38
 */
public class LogUtil {

    private static final String TAG = "=RxBestPractices=";
    private static boolean mEnableFileLog = false;
    private static boolean mEnableLog = true;
    private static boolean mDebug = true;
    private static String mDirpath;
    private static String mFilename;
    private static boolean mIsAlreadyValid = false;
    private static final long MAX_FILE_SIZE = 100 * 1024;
    /**
     * 异常栈位移
     */
    private static final int EXCEPTION_STACK_INDEX = 2;

    private static final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private static ExecutorService mExecutorService = Executors.newSingleThreadExecutor();

    public static void e(String tag, String msg) {
        if (mEnableLog) {
            if (mDebug) {
                Log.e(TAG, tag + getTag() + " " + msg);
            } else {
                Log.e(tag, msg);
            }
            if (mEnableFileLog) {
                cacheLogLocal(tag + " " + getTag() + " " + msg);
            }
        }
    }

    public static void e(String tag, String msg, Throwable tr) {
        if (mEnableLog) {
            if (mDebug) {
                Log.e(TAG, tag + getTag() + " " + msg, tr);
            } else {
                Log.e(tag, msg, tr);
            }
            if (mEnableFileLog) {
                cacheLogLocal(tag + " " + getTag() + " " + msg + " " + tr.getMessage());
            }
        }
    }

    public static void d(String tag, String msg) {
        if (mEnableLog) {
            if (mDebug) {
                Log.d(TAG, tag + " " + getTag() + " " + msg);
            } else {
                Log.d(tag, msg);
            }
            if (mEnableFileLog) {
                cacheLogLocal(tag + " " + getTag() + " " + msg);
            }
        }
    }

    public static void d(String tag, String msg, Throwable tr) {
        if (mEnableLog) {
            if (mDebug) {
                Log.d(TAG, tag + getTag() + " " + msg, tr);
            } else {
                Log.d(tag, msg, tr);
            }
            if (mEnableFileLog) {
                cacheLogLocal(tag + " " + getTag() + " " + msg + " " + tr.getMessage());
            }
        }
    }

    public static void i(String tag, String msg) {
        if (mEnableLog) {
            if (mDebug) {
                Log.i(TAG, tag + " " + getTag() + " " + msg);
            } else {
                Log.i(tag, msg);
            }
            if (mEnableFileLog) {
                cacheLogLocal(tag + " " + getTag() + " " + msg);
            }
        }
    }

    public static void i(String tag, String msg, Throwable tr) {
        if (mEnableLog) {
            if (mDebug) {
                Log.i(TAG, tag + getTag() + " " + msg, tr);
            } else {
                Log.i(tag, msg, tr);
            }
            if (mEnableFileLog) {
                cacheLogLocal(tag + " " + getTag() + " " + msg + " " + tr.getMessage());
            }
        }
    }

    public static void v(String tag, String msg) {
        if (mEnableLog) {
            if (mDebug) {
                Log.v(TAG, tag + " " + msg);
            } else {
                Log.v(tag, msg);
            }
            if (mEnableFileLog) {
                cacheLogLocal(tag + " " + getTag() + " " + msg);
            }
        }
    }

    public static void v(String tag, String msg, Throwable tr) {
        if (mEnableLog) {
            if (mDebug) {
                Log.v(TAG, tag + getTag() + " " + msg, tr);
            } else {
                Log.v(tag, msg, tr);
            }
            if (mEnableFileLog) {
                cacheLogLocal(tag + " " + getTag() + " " + msg + " " + tr.getMessage());
            }
        }
    }

    public static void w(String tag, String msg) {
        if (mEnableLog) {
            if (mDebug) {
                Log.w(TAG, tag + getTag() + " " + msg);
            } else {
                Log.w(tag, msg);
            }
            if (mEnableFileLog) {
                cacheLogLocal(tag + " " + getTag() + " " + msg);
            }
        }
    }

    public static void w(String tag, Throwable tr) {
        if (mEnableLog) {
            if (mDebug) {
                Log.w(TAG, tag + getTag() + " " + tr);
            } else {
                Log.w(tag, tr);
            }
            if (mEnableFileLog) {
                cacheLogLocal(tag + " " + getTag() + " " + tr.getMessage());
            }
        }
    }

    public static void w(String tag, String msg, Throwable tr) {
        if (mEnableLog) {
            if (mDebug) {
                Log.w(TAG, tag + getTag() + " " + msg, tr);
            } else {
                Log.w(tag, msg, tr);
            }
            if (mEnableFileLog) {
                cacheLogLocal(tag + " " + getTag() + " " + msg + " " + tr.getMessage());
            }
        }
    }

    public static void wtf(String tag, String msg) {
        if (mEnableLog) {
            if (mDebug) {
                Log.wtf(TAG, tag + getTag() + " " + msg);
            } else {
                Log.wtf(tag, msg);
            }
            if (mEnableFileLog) {
                cacheLogLocal(tag + " " + getTag() + " " + msg);
            }
        }
    }

    public static void wtf(String tag, Throwable tr) {
        if (mEnableLog) {
            if (mDebug) {
                Log.wtf(TAG, tr + getTag() + " " + tr);
            } else {
                Log.wtf(tag, tr);
            }
            if (mEnableFileLog) {
                cacheLogLocal(tag + " " + getTag() + " " + tr.getMessage());
            }
        }
    }

    public static void wtf(String tag, String msg, Throwable tr) {
        if (mEnableLog) {
            if (mDebug) {
                Log.wtf(TAG, tag + getTag() + " " + msg, tr);
            } else {
                Log.wtf(tag, msg, tr);
            }
            if (mEnableFileLog) {
                cacheLogLocal(tag + " " + getTag() + " " + msg + " " + tr.getMessage());
            }
        }
    }

    /**
     * 将日志缓存到本地，用于错误诊断
     *
     * @param message
     */
    private static void cacheLogLocal(final String message) {
        final File logFile = validateLogFile();
        if (logFile == null) {
            LogUtil.d(TAG, "Illegal file path");
            return;
        }

        Date curDate = new Date(System.currentTimeMillis());
        final String msg;
        synchronized (LogUtil.class) {
            msg = formatter.format(curDate) + " " + TAG + message;
        }

        mExecutorService.execute(() -> {
            FileWriter writer = null;
            try {
                writer = new FileWriter(logFile, true);
                writer.write(msg + "\n");
                writer.flush();

            } catch (Exception e) {

            } finally {
                if (writer != null) {
                    try {
                        writer.close();
                    } catch (Exception e) {
                    }
                }
            }
        });
    }

    private static File validateLogFile() {
        if (TextUtils.isEmpty(mDirpath)) {
            mDirpath = Constants.LOG_CACHE_PATH;
        }
        File dir = new File(mDirpath);
        if (!dir.exists()) {
            if (!dir.mkdirs()) {
                return null;
            }
        }

        if (TextUtils.isEmpty(mFilename)) {
            mFilename = Constants.LOG_CACHE_FILE_NAME;
        }
        // TODO(ZFY): 2018/3/1 检查此处逻辑
        File logFile = new File(dir, mFilename);
        if (mIsAlreadyValid) {
            return logFile;
        }
        if (!logFile.exists()) {
            try {
                logFile.createNewFile();

                mIsAlreadyValid = true;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        } else {
            mIsAlreadyValid = true;
            try {
                FileInputStream fis = null;
                fis = new FileInputStream(logFile);
                long size = fis.available();
                if (size > MAX_FILE_SIZE) {
                    logFile.delete();
                    logFile.createNewFile();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return logFile;
    }

    /**
     * 获取日志的标签 格式：类名_方法名_行号 （需要权限：android.permission.GET_TASKS）
     *
     * @return tag
     * @see [类、类#方法、类#成员]
     */
    public static String getTag() {
        try {
            Exception exception = new Exception();
            if (exception.getStackTrace() == null
                    || exception.getStackTrace().length <= EXCEPTION_STACK_INDEX) {
                return "***";
            }
            StackTraceElement element = exception.getStackTrace()[EXCEPTION_STACK_INDEX];
            String className = element.getClassName();
            int index = className.lastIndexOf(".");
            if (index > 0) {
                className = className.substring(index + 1);
            }
            return className + "_" + element.getMethodName() + "_"
                    + element.getLineNumber();
        } catch (Throwable e) {
            e.printStackTrace();
            return "***";
        }
    }
}
