package task.ab.com.task.task;

import android.os.AsyncTask;

import java.util.List;

/**
 * Created by yang.ding on 2016/4/25.
 */
public class AbTask extends AsyncTask<AbTaskItem, Integer, AbTaskItem> {

    /** 监听器. */
    private AbTaskListener listener;

    /** 结果. */
    private Object result;

    /**
     * 初始化Task.
     */
    public AbTask() {
        super();
    }

    /**
     * 实例化.
     */
    public static AbTask newInstance() {
        AbTask mAbTask = new AbTask();
        return mAbTask;
    }

    /**
     *
     * 执行任务.
     * @param items
     * @return
     */
    @Override
    protected AbTaskItem doInBackground(AbTaskItem... items) {
        AbTaskItem item = items[0];
        this.listener = item.getListener();
        if (this.listener != null) {
            if(this.listener instanceof AbTaskListListener){
                result = ((AbTaskListListener)this.listener).getList();
            }else if(this.listener instanceof AbTaskObjectListener){
                result = ((AbTaskObjectListener)this.listener).getObject();
            }else{
                this.listener.get();
            }
        }
        return item;
    }

    /**
     *
     * 取消.
     */
    @Override
    protected void onCancelled() {
        super.onCancelled();
    }

    /**
     *
     * 执行完成.
     * @param item
     */
    @Override
    protected void onPostExecute(AbTaskItem item) {
        if (this.listener != null) {
            if(this.listener instanceof AbTaskListListener){
                ((AbTaskListListener)this.listener).update((List<?>)result);
            }else if(this.listener instanceof AbTaskObjectListener){
                ((AbTaskObjectListener)this.listener).update(result);
            }else{
                this.listener.update();
            }
        }
    }

    /**
     *
     * 执行前.
     */
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    /**
     *
     * 进度更新.
     * @param values
     */
    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        if (this.listener != null) {
            this.listener.onProgressUpdate(values);
        }
    }

}
