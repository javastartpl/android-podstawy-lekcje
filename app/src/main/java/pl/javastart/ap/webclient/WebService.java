package pl.javastart.ap.webclient;

import java.util.List;

import retrofit2.http.GET;


public interface WebService {

    @GET("categories")
    List<Category> getAllCategories();



}
