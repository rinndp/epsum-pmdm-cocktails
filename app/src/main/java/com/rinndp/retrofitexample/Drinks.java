package com.rinndp.retrofitexample;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;

public class Drinks {

    @SerializedName("drinks")
    List<Cocktail> drinks = new ArrayList<>();

    public static class Cocktail {
        @SerializedName("strDrink")
        public String cocktailName;
        @SerializedName("strDrinkThumb")
        public String cocktailImageUrl;
        @SerializedName("idDrink")
        public String cocktailId;

        public Cocktail (String name, String imageUrl, String cocktailId) {
            this.cocktailName = name;
            this.cocktailImageUrl = imageUrl;
            this.cocktailId = cocktailId;
        }

        public String getCocktailName() {
            return cocktailName;
        }

        public String getCocktailImageUrl() {
            return cocktailImageUrl;
        }

        public String getCocktailId() {
            return cocktailId;
        }
    }

    public List<Cocktail> getDrinks() {
        return drinks;
    }
}
