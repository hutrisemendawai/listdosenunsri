package com.example.listdosen;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.imageview.ShapeableImageView;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        // Retrieve the position of the selected item
        int position = getIntent().getIntExtra("POSITION", -1);

        // Retrieve the strings directly from the string arrays based on the selected position
        String[] dataNames = getResources().getStringArray(R.array.data_name);
        String[] extraData1Array = getResources().getStringArray(R.array.extra_data_1);
        String[] extraData2Array = getResources().getStringArray(R.array.extra_data_2);
        String[] extraData3Array = getResources().getStringArray(R.array.extra_data_3);

        // Retrieve the specific strings from the arrays based on the selected position
        String dataName = dataNames[position];
        String extraData1 = extraData1Array[position];
        String extraData2 = extraData2Array[position];
        String extraData3 = extraData3Array[position];

        // Retrieve the passed Dosen object from the Intent
        Dosen dosen = getIntent().getParcelableExtra("DOSEN");
        int photoResourceId = dosen.getPhoto();

        // Use dosen object and extra data to populate the UI with details
        TextView nameTextView = findViewById(R.id.nameTextView);
        TextView extraData1TextView = findViewById(R.id.extraData1TextView);
        TextView extraData2TextView = findViewById(R.id.extraData2TextView);
        TextView extraData3TextView = findViewById(R.id.extraData3TextView);
        ShapeableImageView imgDosenPhoto = findViewById(R.id.imgDosenPhoto);

        nameTextView.setText(dataName);
        extraData1TextView.setText(extraData1);
        extraData2TextView.setText(extraData2);
        extraData3TextView.setText(extraData3);
        imgDosenPhoto.setImageResource(photoResourceId);

        // Set other UI elements with corresponding dosen properties and extra data
    }
}
