package com.kkandroidstudy.activity;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.kkandroidstudy.R;

import java.io.IOException;

public class SoundPoolActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sound_pool);

        final float playbackSpeed = 1.5f;
        final SoundPool soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 100);
        try {
            final int soundId=soundPool.load(getAssets().openFd("soundpool.mp3"), 1);
            AudioManager mgr = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
            final float volume = mgr.getStreamMaxVolume(AudioManager.STREAM_MUSIC);

            soundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener()
            {
                @Override
                public void onLoadComplete(SoundPool arg0, int arg1, int arg2)
                {
                    soundPool.play(soundId, volume, volume, 1, -1, 1);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
