package com.example.musicplayer;

import androidx.appcompat.app.AppCompatActivity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;
    private boolean isPaused = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get a reference to the music file in the raw folder
        int musicResId = R.raw.your_music_file;

        // Initialize the MediaPlayer
        mediaPlayer = MediaPlayer.create(this, musicResId);

        Button buttonPlay = findViewById(R.id.buttonPlay);
        Button buttonPause = findViewById(R.id.buttonPause);
        Button buttonStop = findViewById(R.id.buttonStop);

        buttonPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mediaPlayer.isPlaying()) {
                    if (isPaused) {
                        mediaPlayer.start();
                        isPaused = false;
                    } else {
                        mediaPlayer.prepareAsync();
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mediaPlayer) {
                                mediaPlayer.start();
                            }
                        });
                    }
                }
            }
        });

        buttonPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                    isPaused = true;
                }
            }
        });

        buttonStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.stop();
                    mediaPlayer.prepareAsync();
                    isPaused = false;
                }
            }
        });
    }

    // Your activity's other methods and overrides
    // ...

    @Override
    protected void onDestroy() {
        // Release the MediaPlayer when the activity is destroyed
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
        super.onDestroy();
    }
}
