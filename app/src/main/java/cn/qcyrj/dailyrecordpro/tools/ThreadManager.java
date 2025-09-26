package cn.qcyrj.dailyrecordpro.tools;

import android.os.Handler;
import android.os.Looper;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 线程管理工具类
 * 提供后台线程执行和主线程回调功能
 */
public class ThreadManager {
    // 创建一个固定大小的线程池
    private static final ExecutorService executor = Executors.newFixedThreadPool(3);
    
    // 主线程Handler，用于切换回主线程更新UI
    private static final Handler mainHandler = new Handler(Looper.getMainLooper());
    
    /**
     * 在后台线程执行任务
     * @param runnable 需要执行的任务
     */
    public static void executeInBackground(Runnable runnable) {
        executor.execute(runnable);
    }
    
    /**
     * 在主线程执行任务（通常用于更新UI）
     * @param runnable 需要执行的任务
     */
    public static void executeOnMainThread(Runnable runnable) {
        mainHandler.post(runnable);
    }
    
    /**
     * 在后台线程执行任务，并在主线程返回结果
     * @param backgroundTask 后台执行的任务
     * @param mainThreadCallback 在主线程执行的回调
     * @param <T> 返回值类型
     */
    public static <T> void executeTaskWithCallback(
            BackgroundTask<T> backgroundTask, 
            MainThreadCallback<T> mainThreadCallback) {
        
        executeInBackground(() -> {
            try {
                T result = backgroundTask.run();
                executeOnMainThread(() -> mainThreadCallback.onComplete(result));
            } catch (Exception e) {
                executeOnMainThread(() -> mainThreadCallback.onError(e));
            }
        });
    }
    
    /**
     * 后台任务接口
     * @param <T> 返回值类型
     */
    public interface BackgroundTask<T> {
        T run() throws Exception;
    }
    
    /**
     * 主线程回调接口
     * @param <T> 返回值类型
     */
    public interface MainThreadCallback<T> {
        void onComplete(T result);
        default void onError(Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * 关闭线程池，释放资源
     */
    public static void shutdown() {
        if (!executor.isShutdown()) {
            executor.shutdown();
        }
    }
}