package com.humanheima.hmsimplebanner.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.humanheima.hmsimplebanner.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    public static void launch(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

    @OnClick({R.id.btn_simple_activity, R.id.btn_transform_activity})
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
