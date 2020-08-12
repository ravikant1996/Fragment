package com.example.dialpad;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ListFrag.ItemSelected {
    String[] description;
    TextView tvDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (getIntent().getData() != null) {
            Toast.makeText(this, "Data Received: " + getIntent().getData().toString(), Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(this, "No Data Received", Toast.LENGTH_SHORT).show();
        }

        tvDescription = findViewById(R.id.tvDescription);
        description = getResources().getStringArray(R.array.descriptions);
        //the phone is in portrait mode
        if (findViewById(R.id.layout_portrait) != null) {
            FragmentManager manager = this.getSupportFragmentManager();
            manager.beginTransaction()
                    .hide(manager.findFragmentById(R.id.detailfrag))
                    .show(manager.findFragmentById(R.id.listfrag))
                    .commit();
        }
        //the phone is in land mode
        if (findViewById(R.id.layout_land) != null) {
            FragmentManager manager = this.getSupportFragmentManager();
            manager.beginTransaction()
                    .show(manager.findFragmentById(R.id.detailfrag))
                    .show(manager.findFragmentById(R.id.listfrag))
                    .commit();


        }


    }

    @Override
    public void onItemSelected(int index) {
        tvDescription.setText(description[index]);

        if (findViewById(R.id.layout_portrait) != null){
            FragmentManager manager = this.getSupportFragmentManager();
            manager.beginTransaction()
                    .show(manager.findFragmentById(R.id.detailfrag))
                    .hide(manager.findFragmentById(R.id.listfrag))
                    .addToBackStack(null)
                    .commit();
        }


    }
}