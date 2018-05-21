package com.example.chintanpatel.sensorlist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        SensorDisplayFragment sensorDisplay =
                (SensorDisplayFragment) getSupportFragmentManager()
                        .findFragmentById(R.id.frag_sensor_view);
        SensorSelectorFragment sensorSelect =
                (SensorSelectorFragment) getSupportFragmentManager()
                        .findFragmentById(R.id.frag_sensor_select);
        sensorSelect.setSensorDisplay(sensorDisplay);
    }
}
