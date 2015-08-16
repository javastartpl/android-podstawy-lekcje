package pl.javastart.ap.webclient;

import java.util.List;

import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.DELETE;
import retrofit.http.GET;
import retrofit.http.Path;

public interface CategoryRetrofitService {

    @GET("/categories")
    Callback<List<Category>> getAll();

    @GET("/categories")
    void getAll(Callback<List<Category>> callback);

    @DELETE("/categories/{id}")
    void delete(@Path("id") long categoryId, Callback<Response> callback);
}

