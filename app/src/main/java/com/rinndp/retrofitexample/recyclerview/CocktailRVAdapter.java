package com.rinndp.retrofitexample.recyclerview;

import android.content.Context;
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
import com.rinndp.retrofitexample.Drinks;
import com.rinndp.retrofitexample.R;

public class CocktailRVAdapter extends ListAdapter<Drinks.Cocktail, CocktailRVAdapter.CocktailViewHolder> {
    Context context;
    View view;

    public CocktailRVAdapter (@NonNull DiffUtil.ItemCallback<Drinks.Cocktail> diffCallBack, Context context) {
        super(diffCallBack);
        this.context = context;
    }

    @NonNull
    @Override
    public CocktailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        this.view = inflater.inflate(R.layout.cocktail_cardview, parent, false);
        return CocktailViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull CocktailViewHolder holder, int position) {
        Drinks.Cocktail cocktail = getItem(position);
        holder.bind(cocktail.getCocktailName(), cocktail.cocktailImageUrl);
    }

    public static class CocktailDiff extends DiffUtil.ItemCallback<Drinks.Cocktail> {

        @Override
        public boolean areItemsTheSame(@NonNull Drinks.Cocktail oldItem, @NonNull Drinks.Cocktail newItem) {
            return false;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Drinks.Cocktail oldItem, @NonNull Drinks.Cocktail newItem) {
            return false;
        }
    }

    public static class CocktailViewHolder extends RecyclerView.ViewHolder {
        ImageView cocktailIV;
        TextView cocktailTV;

        public CocktailViewHolder(@NonNull View itemView) {
            super(itemView);
            cocktailTV = itemView.findViewById(R.id.cocktailTextVIew);
            cocktailIV = itemView.findViewById(R.id.cocktailImageView);
        }

        static CocktailViewHolder create(ViewGroup parent) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.cocktail_cardview, parent, false);
            return new CocktailViewHolder(view);
        }

        public void bind(String name, String imageUrl) {
            cocktailTV.setText(name);

            Glide.with(itemView)
                    .load(imageUrl)
                    .centerCrop()
                    .transition(DrawableTransitionOptions.withCrossFade(500))
                    .into(cocktailIV);
        }
    }
}
