package com.example.listdosen;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.imageview.ShapeableImageView;

public class DetailsActivity extends AppCompatActivity {
    private FloatingActionButton fabBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        // Retrieve the position of the selected item
        int position = getIntent().getIntExtra("POSITION", -1);
        Intent intent = getIntent();
        String description = intent.getStringExtra("DESCRIPTION");

        // Retrieve the specific strings from the arrays based on the selected position
        String[] dataNames = getResources().getStringArray(R.array.data_name);
        String[] extraData1Array = getResources().getStringArray(R.array.extra_data_1);
        String[] extraData2Array = getResources().getStringArray(R.array.extra_data_2);
        String[] extraData3Array = getResources().getStringArray(R.array.extra_data_3);

        // Retrieve the passed Dosen object from the Intent
        Dosen dosen = getIntent().getParcelableExtra("DOSEN");
        int photoResourceId = dosen.getPhoto();

        // Use dosen object and extra data to populate the UI with details
        TextView nameTextView = findViewById(R.id.nameTextView);
        TextView tvAppNames = findViewById(R.id.tvAppNames);
        TextView extraData1TextView = findViewById(R.id.extraData1TextView);
        TextView extraData2TextView = findViewById(R.id.extraData2TextView);
        TextView extraData3TextView = findViewById(R.id.extraData3TextView);
        ShapeableImageView imgDosenPhoto = findViewById(R.id.imgDosenPhoto);

        nameTextView.setText(dataNames[position]);
        extraData1TextView.setText(extraData1Array[position]);
        extraData2TextView.setText(extraData2Array[position]);
        extraData3TextView.setText(extraData3Array[position]);
        imgDosenPhoto.setImageResource(photoResourceId);
        tvAppNames.setText(description);

        // Set other UI elements with corresponding dosen properties and extra data
    }
}

