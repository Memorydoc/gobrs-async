package depend;


import com.gobrs.platform.async.callback.ICallback;
import com.gobrs.platform.async.callback.ITask;
import com.gobrs.platform.async.worker.TaskResult;
import com.gobrs.platform.async.wrapper.TaskWrapper;

import java.util.Map;

/**
 * @author sizegang wrote on 2019-11-20.
 */
public class DeTask1 implements ITask<TaskResult<User>, User>, ICallback<TaskResult<User>, User> {

    @Override
    public User doTask(TaskResult<User> result, Map<String, TaskWrapper> allWrappers) {
        System.out.println("par1的入参来自于par0： " + result.getResult());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new User("user1");
    }


    @Override
    public User defaultValue() {
        return new User("default User");
    }

    @Override
    public void begin() {
        //System.out.println(Thread.currentThread().getName() + "- start --" + System.currentTimeMillis());
    }

    @Override
    public void result(boolean success, TaskResult<User> param, TaskResult<User> workResult) {
        System.out.println("worker1 的结果是：" + workResult.getResult());
    }

}
