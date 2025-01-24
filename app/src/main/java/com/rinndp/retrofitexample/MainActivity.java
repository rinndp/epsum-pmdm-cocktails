package com.rinndp.retrofitexample;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rinndp.retrofitexample.recyclerview.CocktailRVAdapter;

import java.util.Collection;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    List<Drinks.Cocktail> mListaCocktails;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerViewCocktails);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        this.progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);

        Button filterButton = findViewById(R.id.filterButton);

        filterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText filterEditText = findViewById(R.id.TextInputEditTextFilter);
                String alcoholType = filterEditText.getText().toString();
                progressBar.setVisibility(View.VISIBLE);

                Call<Drinks> call = apiInterface.getDrinksByLicour(alcoholType);
                call.enqueue(new Callback<Drinks>() {
                    @Override
                    public void onResponse(Call<Drinks> call, Response<Drinks> response) {
                        Drinks drinks = response.body();

                        if (drinks != null)
                            mListaCocktails = drinks.getDrinks();

                        CocktailRVAdapter adapter = new CocktailRVAdapter(mListaCocktails, MainActivity.this);
                        recyclerView.setAdapter(adapter);
                        progressBar.setVisibility(View.INVISIBLE);
                    }

                    @Override
                    public void onFailure(Call<Drinks> call, Throwable throwable) {
                        Log.d("CALL -> mal", throwable.toString());
                    }
                });
            }
        });
    }
}
