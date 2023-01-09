package com.lbo.basicsound;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity {
    private SoundPool mSoundPool;
    private int mSound;
    private int streamId;
    private AudioManager audioManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSoundPool = new SoundPool.Builder().build();
        mSound = mSoundPool.load(this, R.raw.test1, 1);
        audioManager = (AudioManager) getSystemService(getApplicationContext().AUDIO_SERVICE);
        SeekBar sbVolumn = (SeekBar)findViewById(R.id.seekbar_volumn);
        sbVolumn.setMax(audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC));

        Button btnPlay = (Button)findViewById(R.id.button_play);
        btnPlay.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                playSound();
            }
        });
        Button btnStop = (Button)findViewById(R.id.button_stop);
        btnPlay.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                stopSound();
            }
        });
        sbVolumn.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                changeVolumn(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }
    public void playSound(){
        streamId = this.mSoundPool.play(mSound, 1.0f, 1.0f, 0, 0, 1.0f);
    }
    public void stopSound(){
        mSoundPool.stop(streamId);
    }
    public void changeVolumn(int progress){
        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, progress, AudioManager.FLAG_SHOW_UI);
    }
}