package pl.javastart.ap.webclient;

import java.util.List;

import retrofit.http.GET;

public interface WebService {

    @GET("/categories")
    List<Category> getAllCategories();



}
