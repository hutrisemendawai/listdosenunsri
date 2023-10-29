package com.example.listdosen;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;

public class ListDosenAdapter extends RecyclerView.Adapter<ListDosenAdapter.ListViewHolder> {
    private ArrayList<Dosen> listDosen;
    private ArrayList<Dosen> filteredList;
    private Context context;
    private int selectedPosition = RecyclerView.NO_POSITION;
    private ArrayList<Dosen> originalList;

    public void setFilteredList(ArrayList<Dosen> filteredList){
        this.listDosen = filteredList;
        notifyDataSetChanged();
    }

    public ListDosenAdapter(Context context, ArrayList<Dosen> list) {
        this.context = context;
        this.listDosen = list;
        this.originalList = list;
        this.filteredList = new ArrayList<>(list);
    }


    private OnItemClickCallback onItemClickCallback;
    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row_dosen, parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ListViewHolder holder, int position) {
        Dosen dosen = listDosen.get(position);
        holder.imgPhoto.setImageResource(dosen.getPhoto());
        holder.tvName.setText(dosen.getName());

        // Set an OnClickListener for the item
        holder.itemView.setOnClickListener(view -> {
            // Get the selected Dosen object from the original list
            Dosen selectedDosen = originalList.get(originalList.indexOf(dosen));

            // Find the position of the selectedDosen in the original list
            int originalPosition = originalList.indexOf(selectedDosen);

            String[] descriptions = context.getResources().getStringArray(R.array.description);
            String description = descriptions[originalPosition];

            // Create an Intent to start DetailsActivity
            Intent intent = new Intent(view.getContext(), DetailsActivity.class);

            // Pass the selected Dosen object and original position to DetailsActivity
            intent.putExtra("DOSEN", selectedDosen);
            intent.putExtra("POSITION", originalPosition);
            intent.putExtra("DESCRIPTION", description);

            // Start DetailsActivity with the selected Dosen object and position
            view.getContext().startActivity(intent);

        });
    }


    @Override
    public int getItemCount() {
        return listDosen.size();
    }

    public interface OnItemClickCallback {
        void onItemClicked(Dosen data);
    }
    class ListViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPhoto;
        TextView tvName;

        ListViewHolder(View itemView) {
            super(itemView);
            imgPhoto = itemView.findViewById(R.id.img_item_photo);
            tvName = itemView.findViewById(R.id.tv_item_name);
        }
    }
}