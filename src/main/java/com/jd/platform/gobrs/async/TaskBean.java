package com.jd.platform.gobrs.async;

import com.jd.platform.gobrs.async.callback.ICallback;
import com.jd.platform.gobrs.async.callback.IWorker;
import com.jd.platform.gobrs.async.gobrs.GobrsTaskFlow;
import com.jd.platform.gobrs.async.worker.TaskResult;

import java.util.Map;

/**
 * @program: gobrs-async
 * @ClassName TaskBean
 * @description:
 * @author: sizegang
 * @create: 2022-01-26 23:41
 * @Version 1.0
 **/

public class TaskBean implements IWorker, ICallback {
    private String name;

    GobrsTaskFlow gobrsTaskFlow;


    @Override
    public void result(boolean success, Object param, TaskResult workResult) {
    }

    @Override
    public Object action(Object object, Map allWrappers) {
        return null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
