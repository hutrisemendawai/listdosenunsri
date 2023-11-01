package com.example.listdosen;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.imageview.ShapeableImageView;

public class DetailsActivity extends AppCompatActivity {
    private int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);



        Intent intent = getIntent();
        position = intent.getIntExtra("POSITION", -1);
        String description = intent.getStringExtra("DESCRIPTION");


        String[] dataNames = getResources().getStringArray(R.array.data_name);
        String[] extraData1Array = getResources().getStringArray(R.array.extra_data_1);
        String[] extraData2Array = getResources().getStringArray(R.array.extra_data_2);
        String[] extraData3Array = getResources().getStringArray(R.array.extra_data_3);
        String[] extraData4Array = getResources().getStringArray(R.array.extra_data_4);
        String[] extraData5Array = getResources().getStringArray(R.array.extra_data_5);
        String[] extraData6Array = getResources().getStringArray(R.array.extra_data_6);


        Dosen dosen = getIntent().getParcelableExtra("DOSEN");
        int photoResourceId = dosen.getPhoto();


        TextView nameTextView = findViewById(R.id.nameTextView);
        TextView tvAppNames = findViewById(R.id.tvAppNames);
        TextView extraData1TextView = findViewById(R.id.extraData1TextView);
        TextView extraData2TextView = findViewById(R.id.extraData2TextView);
        TextView extraData3TextView = findViewById(R.id.extraData3TextView);
        TextView extraData4TextView = findViewById(R.id.extraData4TextView);
        TextView extraData5TextView = findViewById(R.id.extraData5TextView);
        TextView extraData6TextView = findViewById(R.id.extraData6TextView);
        ShapeableImageView imgDosenPhoto = findViewById(R.id.imgDosenPhoto);

        nameTextView.setText(dataNames[position]);
        extraData1TextView.setText(extraData1Array[position]);
        extraData2TextView.setText(extraData2Array[position]);
        extraData3TextView.setText(extraData3Array[position]);
        extraData4TextView.setText(extraData4Array[position]);
        extraData5TextView.setText(extraData5Array[position]);
        extraData6TextView.setText(extraData6Array[position]);
        imgDosenPhoto.setImageResource(photoResourceId);
        tvAppNames.setText(description);


    }

    public void onImageButtonClick(View view) {
        finish();
    }

    public void onCallClick(View view) {
        String[] phoneNumbers = getResources().getStringArray(R.array.extra_data_6);
        String phoneNumber = phoneNumbers[position];

        Intent callIntent = new Intent(Intent.ACTION_DIAL);
        callIntent.setData(Uri.parse("tel:" + phoneNumber));
        startActivity(callIntent);
    }

    public void onEmailClick(View view) {

        String[] emails = getResources().getStringArray(R.array.extra_data_5);
        String email = emails[position];

        Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
        emailIntent.setData(Uri.parse("mailto:" + email));
        startActivity(emailIntent);
    }
}

