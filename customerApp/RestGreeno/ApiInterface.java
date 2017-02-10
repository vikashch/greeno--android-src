package com.green0.customerApp.RestGreeno;

/**
 * Created by ironman on 10/11/16.
 */

import com.green0.customerApp.model.ImageResultDisease;
import com.green0.customerApp.model.UserLogin;
import com.green0.customerApp.model.UserProfile;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ApiInterface {
//    @GET("movie/top_rated")
//    Call<MoviesResponse> getTopRatedMovies(@Query("api_key") String apikey);
//
//    @GET("movie/{id}")
//    Call<MoviesResponse> getMovieDetails(@Path("id") int id,@Query("api_key") String apikey);

    @POST("/login")
    Call<UserProfile>  AuthenticateUser(@Body UserLogin userLogin);

//    @Multipart
//    @POST("/v0/upload")
//    Call<ResponseBody> postImage(@Part MultipartBody.Part image);

    @Multipart
    @POST("/v0/upload")
    Call<List<ImageResultDisease>> postImages(@Part MultipartBody.Part file);
}