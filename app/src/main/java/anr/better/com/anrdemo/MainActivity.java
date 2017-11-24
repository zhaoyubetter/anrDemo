package anr.better.com.anrdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import anr.better.com.anrdemo.constraint.ContraintMainActivity;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_main_thread).setOnClickListener(this);
        findViewById(R.id.btn_broad_cast).setOnClickListener(this);
        findViewById(R.id.btn_test_constraint).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_main_thread:
                anrMainThread();
                break;
            case R.id.btn_broad_cast:
                anrBroadCast();
                break;
            case R.id.btn_test_constraint:
                startActivity(new Intent(getApplicationContext(), ContraintMainActivity.class));
                break;
        }
    }

    private void anrBroadCast() {
        LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(new Intent("better.test"));
        IntentFilter filter = new IntentFilter("better.test");
        LocalBroadcastManager.getInstance(getApplicationContext()).registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                SystemClock.sleep(20 * 1000);
                Toast.makeText(getApplicationContext(), "广播", Toast.LENGTH_SHORT).show();
            }
        },filter);

        LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(new Intent("better.test"));
    }

    private void anrMainThread() {
        Toast.makeText(getApplicationContext(), "卡死UI", Toast.LENGTH_SHORT).show();
        SystemClock.sleep(10 * 1000);

    }
}
