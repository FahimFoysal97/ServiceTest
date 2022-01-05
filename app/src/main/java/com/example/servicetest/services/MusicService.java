package com.example.servicetest.services;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.servicetest.R;

public class MusicService extends Service {
    private MediaPlayer player;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        boolean isPause = intent.getBooleanExtra("Pause", false);
        boolean isResume = intent.getBooleanExtra("Resume", false);
        if (player != null && !isPause && !isResume) player.start();
        if (player != null && isPause && player.isPlaying()) player.pause();
        if (player != null && isResume) player.start();
        return START_NOT_STICKY;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("Music", "onCreate: player initiated");
        if (player == null) player = MediaPlayer.create(this, R.raw.give_me_some_sunshine);
        player.setLooping(true);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        player.stop();
        player.release();
        player = null;
    }

}
