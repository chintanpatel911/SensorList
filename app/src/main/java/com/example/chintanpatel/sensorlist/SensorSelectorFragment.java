package com.example.chintanpatel.sensorlist;


import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class SensorSelectorFragment extends ListFragment {
    private static final String TAG = "SensorSelectorFragment";
    private SensorDisplayFragment sensorDisplay;

    public void setSensorDisplay(SensorDisplayFragment sensorDisplay) {
        this.sensorDisplay = sensorDisplay;
        SensorManager sensorManager =
                (SensorManager) getActivity().getSystemService(Activity.SENSOR_SERVICE);
        List<Sensor> sensors = sensorManager.getSensorList(Sensor.TYPE_ALL);
        this.setListAdapter(new SensorListAdapter(getActivity()
                .getApplicationContext(), android.R.layout.simple_list_item_1, sensors));
    }

    private void showSensorFragment(Sensor sensor) {
        sensorDisplay.displaySensor(sensor);
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        ft.hide(this);
        ft.show(sensorDisplay);
        ft.addToBackStack("Showing sensor: " + sensor.getName());
        ft.commit();
    }

    private class SensorListAdapter extends ArrayAdapter<Sensor> {
        public SensorListAdapter(Context context, int textViewResourceId, List<Sensor> sensors) {
            super(context, textViewResourceId, sensors);
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            final Sensor selectedSensor = getItem(position);
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(android.R.layout.simple_list_item_1, null);
            }

            ((TextView) convertView).setText(selectedSensor.getName());
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showSensorFragment(selectedSensor);
                }
            });
            return convertView;
        }
    }
}

