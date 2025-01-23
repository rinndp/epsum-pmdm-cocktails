package com.rinndp.retrofitexample.recyclerview;

import android.content.Context;
import android.content.DialogInterface;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.rinndp.retrofitexample.Drinks;
import com.rinndp.retrofitexample.R;

import java.util.List;

public class CocktailRVAdapter extends RecyclerView.Adapter<CocktailRVAdapter.CocktailViewHolder> {

    View view;
    List<Drinks.Cocktail> mListaCockstails;
    Context context;
    ViewGroup parent;

    public CocktailRVAdapter (List<Drinks.Cocktail> mListaCockstails, Context context) {
        this.mListaCockstails = mListaCockstails;
        this.context = context;
    }

    @NonNull
    @Override
    public CocktailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        this.parent = parent;
        this.view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cocktail_cardview, parent, false);
        return new CocktailViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CocktailViewHolder holder, int position) {
        holder.bind(mListaCockstails.get(position).getCocktailName(), mListaCockstails.get(position).getCocktailImageUrl());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View detailsCocktailView = LayoutInflater.from(context)
                        .inflate(R.layout.cocktail_details, parent, false);

                ImageView detailsImageIV = detailsCocktailView.findViewById(R.id.imageDetailCocktail);
                Glide.with(view)
                        .load(mListaCockstails.get(holder.getAdapterPosition()).getCocktailImageUrl())
                        .transition(DrawableTransitionOptions.withCrossFade(500))
                        .into(detailsImageIV);

                TextView idTextView = detailsCocktailView.findViewById(R.id.idDetailCocktail);
                idTextView.setText(mListaCockstails.get(holder.getAdapterPosition()).cocktailId);

                TextView nameTextView = detailsCocktailView.findViewById(R.id.nameDetailCocktail);
                nameTextView.setText(mListaCockstails.get(holder.getAdapterPosition()).cocktailName);
                TextView instructionsTextView = detailsCocktailView.findViewById(R.id.instructionsDetailCocktail);

                MaterialAlertDialogBuilder materialAlertDialogBuilder = new MaterialAlertDialogBuilder(context)
                        .setView(detailsCocktailView)
                        .setNegativeButton("Salir", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {}
                        });
                materialAlertDialogBuilder.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mListaCockstails.size();
    }


    public static class CocktailViewHolder extends RecyclerView.ViewHolder {
        ImageView cocktailIV;
        TextView cocktailTV;

        public CocktailViewHolder(@NonNull View itemView) {
            super(itemView);
            cocktailTV = itemView.findViewById(R.id.cocktailTextVIew);
            cocktailIV = itemView.findViewById(R.id.cocktailImageView);
        }

        public void bind(String name, String imageUrl) {
            cocktailTV.setText(name);

            Glide.with(itemView)
                    .load(imageUrl)
                    .transition(DrawableTransitionOptions.withCrossFade(500))
                    .into(cocktailIV);
        }
    }
}
