package com.rinndp.retrofitexample;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rinndp.retrofitexample.recyclerview.CocktailRVAdapter;
import com.rinndp.retrofitexample.recyclerview.CocktailViewModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    CocktailViewModel cocktailViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<Drinks> call = apiInterface.getDrinksByLicour("Gin");

        RecyclerView recyclerView = findViewById(R.id.recyclerViewCocktails);
        CocktailRVAdapter adapter = new CocktailRVAdapter(new CocktailRVAdapter.CocktailDiff(), this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        this.cocktailViewModel = new ViewModelProvider(this).get(CocktailViewModel.class);


        call.enqueue(new Callback<Drinks>() {
            @Override
            public void onResponse(Call<Drinks> call, Response<Drinks> response) {
                List<Drinks.Cocktail> mListaCocktail = null;

                Log.d("Código", response.code()+"");
                Drinks drinksResponse = response.body();
                for (Drinks.Cocktail cocktail: drinksResponse.drinks) {
                    Drinks.Cocktail cocktailAñadir = new Drinks.Cocktail(cocktail.cocktailName, cocktail.cocktailImageUrl, cocktail.cocktailId);
                    mListaCocktail.add(cocktailAñadir);
                }
            }

            @Override
            public void onFailure(Call<Drinks> call, Throwable throwable) {
                Log.d("CALL -> mal", throwable.toString());
            }
        });
    }
}
