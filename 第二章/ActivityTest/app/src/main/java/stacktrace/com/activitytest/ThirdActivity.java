package stacktrace.com.activitytest;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.List;

public class ThirdActivity extends BaseActivity {

    private List<Activity> list = ActivityCollector.activities;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_third);
        Button button3 = (Button) findViewById(R.id.button_3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ActivityCollector.finishAll();
                for (Activity activity : list){
                    Log.d("BaseActivity", activity.getClass().getSimpleName());
                }
            }
        });
    }
}
