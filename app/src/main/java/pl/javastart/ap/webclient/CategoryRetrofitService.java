package pl.javastart.ap.webclient;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;

public interface CategoryRetrofitService {

    @GET("/categories")
    Callback<List<Category>> getAll();

    @GET("/categories")
    void getAll(Callback<List<Category>> callback);

}

