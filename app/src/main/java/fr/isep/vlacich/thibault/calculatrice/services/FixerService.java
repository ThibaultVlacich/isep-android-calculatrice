package fr.isep.vlacich.thibault.calculatrice.services;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface FixerService {

    String BASEURL = "http://data.fixer.io/api/";
    String ACCESS_KEY = "<ADD_YOUR_API_KEY_HERE>";

    @GET("/latest?access_key="+ACCESS_KEY)
    Call<FixerLatest> latest(@Query("base") String base, @Query("symbols") String symbol);
}
