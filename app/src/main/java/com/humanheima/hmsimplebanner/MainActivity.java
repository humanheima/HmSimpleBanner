package com.humanheima.hmsimplebanner;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.humanheima.hmsimplebanner.ui.SimpleActivity;
import com.humanheima.hmsimplebanner.ui.TransformActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.btn_simple_activity)
    Button btnSimpleActivity;
    @BindView(R.id.btn_super_activity)
    Button btnSuperActivity;
    @BindView(R.id.btn_another_activity)
    Button btnAnotherActivity;
    @BindView(R.id.btn_transform_activity)
    Button btnTransformActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }


    @OnClick({R.id.btn_indicator_activity, R.id.btn_simple_activity, R.id.btn_super_activity, R.id.btn_another_activity, R.id.btn_transform_activity})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_simple_activity:
                startActivity(new Intent(MainActivity.this, SimpleActivity.class));
                break;
            case R.id.btn_transform_activity:
                startActivity(new Intent(MainActivity.this, TransformActivity.class));
                break;
            default:
                break;
        }
    }
}
