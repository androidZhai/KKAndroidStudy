package com.kkandroidstudy.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

import com.kkandroidstudy.R;
import com.kkandroidstudy.adapter.TitleAdapter;

public class MainActivity extends Activity {
    private String[] titles;
    private ListView listView;
    private TitleAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listView);

        titles = getResources().getStringArray(R.array.titles);
        adapter = new TitleAdapter(this, titles);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        startActivity(TabLayoutViewPagerActivity.class);
                        break;
                    case 1:
                        startActivity(DirectionalViewPagerActivity.class);
                        break;
                    case 2:
                        startActivity(CardViewActivity.class);
                        break;
                    case 3:
                        startActivity(AppBarLayoutActivity.class);
                        break;
                    case 4:
                        startActivity(RecyclerViewPartialRefreshActivity.class);
                        break;
                    case 5:
                        startActivity(CustomCameraActivity.class);
                        break;
                }
            }
        });
    }

    public void startActivity(Class<?> activity) {
        Intent intent = new Intent(this, activity);
        startActivity(intent);
    }
}