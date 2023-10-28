package com.example.listdosen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.SearchView;


import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rvHeroes;
    private ArrayList<Dosen> list = new ArrayList<>();
    private ListDosenAdapter listHeroAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvHeroes = findViewById(R.id.rv_heroes);
        rvHeroes.setHasFixedSize(true);

        list.addAll(getListHeroes());
        showRecyclerList();

        if (getApplicationContext().getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            rvHeroes.setLayoutManager(new GridLayoutManager(this, 2));
        } else {
            rvHeroes.setLayoutManager(new LinearLayoutManager(this));
        }

        SearchView searchView = findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                listHeroAdapter.filter(newText);
                return true;
            }
        });

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

}


