package com.example.zorayda.getuser.connect;

import com.example.zorayda.getuser.seeUser.model.GetUsersResponse;
import com.example.zorayda.getuser.updateUser.model.GeneralResponse;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface Webservice {

    //@GET("get_users.php/")
    @GET("5c106a932e00005a0655b4d1")
    Call<GetUsersResponse> getUsers();

    //@POST("update_users.php/")
    @FormUrlEncoded
    @POST("5c108dad2e00002a0955b537")
    Call<GeneralResponse> updateDataUser(
            @Field("id") String id,
            @Field("name") String name,
            @Field("number") String number);
}
