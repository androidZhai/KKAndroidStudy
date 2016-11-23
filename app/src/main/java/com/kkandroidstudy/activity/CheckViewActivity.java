package com.kkandroidstudy.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.kkandroidstudy.R;
import com.kkandroidstudy.widget.CheckView;

public class CheckViewActivity extends AppCompatActivity {
    private CheckView checkView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_view);

        checkView = (CheckView) findViewById(R.id.checkView);

    }

    public void check(View view) {
        checkView.check();
    }

    public void uncheck(View view) {
        checkView.uncheck();
    }
}
