package com.humanheima.hmsimplebanner.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.humanheima.hmsimplebanner.R;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
