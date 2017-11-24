package anr.better.com.anrdemo.constraint;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import anr.better.com.anrdemo.R;


public class ContraintMainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contraint_main);
        findViewById(R.id.btn_sample1).setOnClickListener(this);
        findViewById(R.id.btn_auto_contraint).setOnClickListener(this);
        findViewById(R.id.btn_me_sample).setOnClickListener(this);
        findViewById(R.id.btn_others).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_sample1:
                startActivity(new Intent(getApplicationContext(), Simple1Activity.class));
                break;
            case R.id.btn_auto_contraint:
                startActivity(new Intent(getApplicationContext(), AutoConstraintActivity.class));
                break;
            case R.id.btn_me_sample:
                startActivity(new Intent(getApplicationContext(), SimpleActivity.class));
                break;
            case R.id.btn_others:
                startActivity(new Intent(getApplicationContext(), OthersActivity.class));
                break;
        }
    }
}
