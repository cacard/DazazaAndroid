package com.dazaza.system;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by cunqingli on 2015/9/11.
 */
public class TaskManager {

    private static ExecutorService executorService = Executors.newFixedThreadPool(3);

    public static void startTask(Runnable r) {
        if (r == null) {
            return;
        }

        executorService.submit(r);
    }

}
