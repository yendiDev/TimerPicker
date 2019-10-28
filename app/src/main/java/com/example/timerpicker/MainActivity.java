package com.example.timerpicker;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.TextClock;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private TimePicker timePicker;
    private TextClock textClock;
    public MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timePicker = findViewById(R.id.timePicker);
        textClock = findViewById(R.id.textClock);
        mediaPlayer = MediaPlayer.create(this,R.raw.alarm);

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {

                if (textClock.getText().toString().equals(AlarmTime()))
                {
                    Toast.makeText(MainActivity.this, "Time's up", Toast.LENGTH_SHORT).show();
                    mediaPlayer.start();
                }
                else {
                    mediaPlayer.stop();
                }

            }
        }, 0, 1000);

    }

    public String AlarmTime() {
        Integer hourTime = timePicker.getCurrentHour();
        Integer minuteTime = timePicker.getCurrentMinute();
        String properMinute;

        if (minuteTime<10)
        {
            properMinute="0";
            properMinute = properMinute.concat(minuteTime.toString());
        }
        else {
            properMinute = minuteTime.toString();
        }

        String fullTime;

        if (hourTime > 12) {
            hourTime = hourTime - 12;
            fullTime = hourTime.toString().concat(":").concat(properMinute).concat(" PM");

        } else {
            fullTime = hourTime.toString().concat(":").concat(properMinute).concat(" AM");
        }

        return fullTime;
    }
}
