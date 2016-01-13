package stacktrace.com.activitylifecycletest;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

public class NormalActivity extends Activity {

    private EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal);
        editText = (EditText) findViewById(R.id.edit_text);
        Log.d("NormalActivity", getClass().getSimpleName());
        if (savedInstanceState != null) {
            String tempData = savedInstanceState.getString("data_key");
            editText.setText(tempData);
        }
        
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        String tempData = editText.getText().toString();
        Log.d("NormalActivity", tempData);
        outState.putString("data_key", tempData);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        Log.d("NormalActivity", "finish!");
    }
}
