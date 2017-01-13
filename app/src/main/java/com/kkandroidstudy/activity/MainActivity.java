package com.kkandroidstudy.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;

import com.kkandroidstudy.R;
import com.kkandroidstudy.adapter.TitleAdapter;
import com.kkandroidstudy.bean.UserBean;
import com.kkandroidstudy.db.DataBaseManager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {
    private String[] titles;
    private ListView listView;
    private TitleAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        SystemClock.sleep(2000);
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
                    case 6:
                        startActivity(FloatingActionButtonActivity.class);
                        break;
                    case 7:
                        startActivity(Retrofit2Activity.class);
                        break;
                    case 8:
                        startActivity(LoggerActivity.class);
                        break;
                    case 9:
                        startActivity(CollapsingToolbarLayoutActivity.class);
                        break;
                    case 10:
                        startActivity(RxJavaTestActivity.class);
                        break;
                    case 11:
                        startActivity(CustomViewPagerActivity.class);
                        break;
                    case 12:
                        startActivity(JNITestActivity.class);
                        break;
                    case 13:
                        startActivity(CanvasActivity.class);
                        break;
                    case 14:
                        startActivity(PieViewActivity.class);
                        break;
                    case 15:
                        startActivity(CheckViewActivity.class);
                        break;
                    case 16:
                        startActivity(RotateViewActivity.class);
                        break;
                    case 17:
                        startActivity(RadarViewActivity.class);
                        break;
                    case 18:
                        startActivity(BezierActivity.class);
                        break;
                    case 19:
                        startActivity(MyView1Activity.class);
                        break;
                    case 20:
                        startActivity(CircleImageViewOneActivity.class);
                        break;
                    case 21:
                        startActivity(RegionViewActivity.class);
                        break;
                    case 22:
                        startActivity(DrawTextActivity.class);
                        break;
                    case 23:
                        startActivity(HttpsActivity.class);
                        break;
                    case 24:
                        startActivity(SoundPoolActivity.class);
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
