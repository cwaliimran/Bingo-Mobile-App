package com.bingoplayer.app.utils;


import com.bingoplayer.app.models.ModelBingoAnswer;
import com.bingoplayer.app.models.ModelBoard;

import org.json.JSONObject;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {

//    @POST("ManagePlayer")
//    @FormUrlEncoded
//    Call<ModelBoard> getBoards(
//            @Field("game-id") String gameid,
//            @Field("session-id") String sessionid,
//            @Field("player-id") String playerid,
//            @Field("op") String op,
//            @Field("ver") String ver
//    );
    @POST("ManagePlayer")
    Call<ModelBoard> getBoards(
            @Query("game-id") String gameid,
            @Query("session-id") String sessionid,
            @Query("player-id") String playerid,
            @Query("op") String op,
            @Query("ver") String ver
    );

    @POST("BingoAnswer/")
    Call<ModelBingoAnswer> submitAnswer(
            @Query("game-id") String gameid,
            @Query("session-id") String sessionid,
            @Query("player-id") String playerid,
            @Query("ver") String ver,
            @Body RequestBody params
    );
/*

    @Multipart
    @FormUrlEncoded
    @POST("BingoAnswer/")
    Call<JSONObject> submitAnswer(
            @Field("game-id") String gameid,
            @Field("session-id") String sessionid,
            @Field("player-id") String playerid,
            @Field("ver") String ver,
            @Body RequestBody params
    );
*/

}
