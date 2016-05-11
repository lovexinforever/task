package task.ab.com.task.task;

/**
 * 名称：AbTaskObjectListener.java
 * 描述：数据执行的接口.
 * Created by yang.ding on 2016/4/25.
 */
public abstract class AbTaskObjectListener extends AbTaskListener{

    /**
     * 执行开始
     * @return 返回的结果对象
     */
    public abstract <T extends Object> T getObject();

    /**
     * 执行开始后调用.
     * @param obj
     */
    public abstract <T extends Object> void update(T obj);


}
