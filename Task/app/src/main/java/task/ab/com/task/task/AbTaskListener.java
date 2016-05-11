package task.ab.com.task.task;

/**
 * 名称：AbTaskListener.java
 * 描述：任务执行的控制父类.
 *
 * Created by yang.ding on 2016/4/25.
 */
public class AbTaskListener {
    /**
     * 执行开始.
     *
     * @return 返回的结果对象
     */
    public void get() {
    }

    /**
     * 执行开始后调用.
     * */
    public void update() {
    }

    /**
     * 监听进度变化.
     *
     * @param values
     *            the values
     */
    public void onProgressUpdate(Integer... values) {
    }

}
