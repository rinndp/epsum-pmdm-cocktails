package com.rinndp.retrofitexample;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("filter.php")
    Call<Drinks> getDrinksByLicour(@Query("i") String licour);
    @GET("lookup.php")
    Call<Drinks> getDrinksById(@Query("i") String id);
}
