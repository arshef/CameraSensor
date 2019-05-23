package com.example.sensorstest;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        startActivity(new Intent(this, Online.class));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SensorManager manager;
        manager = (SensorManager) this.getSystemService(SENSOR_SERVICE);
        List<Sensor> sensors = manager.getSensorList(Sensor.TYPE_ALL);
        for (Sensor sensor :
                sensors) {
            Log.wtf(sensor.getName(), sensor.getVendor());
        }
        Sensor Gyroscope = manager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        manager.registerListener(new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                List<String> strings = new ArrayList<>();
                for (float v :
                        event.values) {
                    strings.add(String.valueOf(v));
                }
                String s = String.join(",", strings);
                Log.d("event:", s);
                if (event.values[2] > 0.5f) Log.wtf("L", "Left");
                else if (event.values[2] < -0.5f) Log.wtf("R", "Right");
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        }, Gyroscope, 1);
    }
}
