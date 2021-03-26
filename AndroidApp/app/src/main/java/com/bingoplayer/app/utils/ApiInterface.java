package com.bingoplayer.app.utils;


import com.bingoplayer.app.models.ModelBoard;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiInterface {

    @POST("ManagePlayer/")
    @FormUrlEncoded
    Call<ModelBoard> getBoards(
            @Field("game-id") String email,
            @Field("session-id") String name,
            @Field("player-id") String mobile,
            @Field("op") String image,
            @Field("ver") String device_id
    );

}
