package mathexa.com;

import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    BluetoothAdapter BA;
    ListView pairedDevicesListView;

    public void turnBluetoothOff(View view) {

        if (pairedDevicesListView != null) {
            pairedDevicesListView.setAdapter(null);
        }
        BA.disable();
        if (BA.isEnabled()) {
            Toast.makeText(getApplicationContext(), "Bluetooth could not be disabled", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getApplicationContext(), "Bluetooth turned off", Toast.LENGTH_LONG).show();
        }

    }


    public void findDiscoverableDevices(View view) {
        Intent i = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
        startActivity(i);
    }

    public void viewPairedDevices(View vie) {


        Set<BluetoothDevice> pairedDevices = BA.getBondedDevices();
        pairedDevicesListView = (ListView) findViewById(R.id.pairedDevicesListView);
        ArrayList pairedDevicesArrayList = new ArrayList();
        for (BluetoothDevice bluetoothDevice : pairedDevices) {
            pairedDevicesArrayList.add(bluetoothDevice.getName());
        }

        ArrayAdapter arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, pairedDevicesArrayList);
        pairedDevicesListView.setAdapter(arrayAdapter);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BA = BluetoothAdapter.getDefaultAdapter();
        if (BA == null) {
            Toast.makeText(getApplicationContext(), "Bluetooth is not supported", Toast.LENGTH_LONG).show();
        } else {

            if (BA.isEnabled()) {
                Toast.makeText(getApplicationContext(), "Bluetooth is on", Toast.LENGTH_LONG).show();
            } else {
                Intent i = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivity(i);
                if (BA.isEnabled()) {
                    Toast.makeText(getApplicationContext(), "Bluetooth turned on", Toast.LENGTH_LONG).show();
                }
            }
        }
    }
}