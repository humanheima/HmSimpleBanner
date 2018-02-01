package com.humanheima.hmsimplebanner.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.humanheima.hmsimplebanner.R;

import butterknife.ButterKnife;

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

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_simple_activity:
                SimpleActivity.launch(this);
                break;
            case R.id.btn_transform_activity:
                TransformActivity.launch(this);
                break;
            default:
                break;
        }
    }
}
