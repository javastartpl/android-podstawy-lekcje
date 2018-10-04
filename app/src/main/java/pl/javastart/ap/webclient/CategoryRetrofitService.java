package pl.javastart.ap.webclient;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CategoryRetrofitService {

    @GET("categories")
    Call<List<Category>> getAll();

    @DELETE("categories/{id}")
    Call<Void> delete(@Path("id") long categoryId);
}

