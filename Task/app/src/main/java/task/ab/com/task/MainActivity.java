package task.ab.com.task;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import task.ab.com.task.task.AbTask;
import task.ab.com.task.task.AbTaskItem;
import task.ab.com.task.task.AbTaskObjectListener;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button btn1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn1 = (Button) findViewById(R.id.btn1);
        btn1.setOnClickListener(this);
        GetContactsInfo getContactsInfo = new GetContactsInfo(this);
        getContactsInfo.getLocalContactsInfos();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn1:
                AbTask task = new AbTask();
                //定义异步执行的对象
                final AbTaskItem item = new AbTaskItem();
                item.setListener(new AbTaskObjectListener(){


                    @Override
                    public String getObject() {
                        Log.e("task","start ----->>>>> ");
                        String message = "hello world";
                        return message;
                    }

                    @Override
                    public <String> void update(String obj) {
                        ((TextView)findViewById(R.id.txt)).setText(obj+"");
                    }
                });

                //执行
                task.execute(item);
                break;
        }
    }
}
