package com.example.listdosen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.SearchView;
import android.widget.TextView;


import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rvHeroes;
    private ArrayList<Dosen> list = new ArrayList<>();
    private ListDosenAdapter listHeroAdapter;

    private SearchView searchView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchView = findViewById(R.id.searchView);
        searchView.clearFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterList(newText);
                return true;
            }
        });
        rvHeroes = findViewById(R.id.rv_heroes);
        rvHeroes.setHasFixedSize(true);

        list.addAll(getListHeroes());
        showRecyclerList();

        if (getApplicationContext().getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            rvHeroes.setLayoutManager(new GridLayoutManager(this, 2));
        } else {
            rvHeroes.setLayoutManager(new LinearLayoutManager(this));
        }


    }



    public ArrayList<Dosen> getListHeroes() {
        String[] dataName = getResources().getStringArray(R.array.data_name);
        TypedArray dataPhoto = getResources().obtainTypedArray(R.array.data_photo);
        ArrayList<Dosen> listHero = new ArrayList<>();
        for (int i = 0; i < dataName.length; i++) {
            Dosen dosen = new Dosen();
            dosen.setName(dataName[i]);
            dosen.setPhoto(dataPhoto.getResourceId(i, -1));
            listHero.add(dosen);
        }
        return listHero;
    }

    private void showRecyclerList() {
        rvHeroes.setLayoutManager(new LinearLayoutManager(this));
        listHeroAdapter = new ListDosenAdapter(this, list);
        rvHeroes.setAdapter(listHeroAdapter);


        listHeroAdapter.setOnItemClickCallback(data -> showSelectedDosen(data));
    }

    private void showSelectedDosen(Dosen dosen) {
        Toast.makeText(this, "Kamu memilih " + dosen.getName(), Toast.LENGTH_SHORT).show();
    }

    public void onBindViewHolder(@NonNull final ListDosenAdapter.ListViewHolder holder, int position) {
        Dosen dosen = list.get(position);
        holder.imgPhoto.setImageResource(dosen.getPhoto());
        holder.tvName.setText(dosen.getName());

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(holder.itemView.getContext(), DetailsActivity.class);
            intent.putExtra("DOSEN", dosen);
            intent.putExtra("EXTRA_DATA_1", "Additional Data 1");
            intent.putExtra("EXTRA_DATA_2", "Additional Data 2");
            intent.putExtra("EXTRA_DATA_3", "Additional Data 3");
            holder.itemView.getContext().startActivity(intent);
        });
    }

    private void filterList(String text) {
        ArrayList<Dosen> filteredList = new ArrayList<>();
        for (Dosen dosen : list) {
            if (dosen.getName().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(dosen);
            }
        }

        if (filteredList.isEmpty()) {
            // If filtered list is empty, set an empty list to the adapter
            listHeroAdapter.setFilteredList(new ArrayList<>());
            // Toast message for data not found
            showToast("Data tidak ditemukan");
        } else {
            // If filtered list is not empty, set the filtered list to the adapter
            listHeroAdapter.setFilteredList(filteredList);
        }
    }
    private void showToast(String message) {
        Toast toast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
        toast.show();

        // Dismiss the toast after a custom duration (e.g., 1.5 seconds)
        Handler handler = new Handler();
        handler.postDelayed(() -> toast.cancel(), 1500); // 1500 milliseconds (1.5 seconds)
    }

}


