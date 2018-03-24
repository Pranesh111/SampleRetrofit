package atrue.pranesh.sampleretrofit2.network;


import java.util.List;

import atrue.pranesh.sampleretrofit2.model.Albums;
import atrue.pranesh.sampleretrofit2.model.Photos;
import atrue.pranesh.sampleretrofit2.model.Users;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiStories {
    @GET("/users")
    Call<List<Users>> doGetListUsers();

    @GET("/albums")
    Call<List<Albums>> doGetalbumsList(@Query("userId") int userId);

    @GET("/photos")
    Call<List<Photos>> doGetPhotoList(@Query("albumId") int albumId);

}
