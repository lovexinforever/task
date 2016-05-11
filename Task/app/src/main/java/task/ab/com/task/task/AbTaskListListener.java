package task.ab.com.task.task;

import java.util.List;

/**
 * Created by yang.ding on 2016/4/25.
 */
public abstract class AbTaskListListener extends AbTaskListener {

    /**
     * 执行开始.
     *
     * @return 返回的结果列表
     */
    public abstract List<?> getList();

    /**
     * 描述：执行完成后回调. 不管成功与否都会执行
     *
     * @param paramList
     *            返回的List
     */
    public abstract void update(List<?> paramList);

}

