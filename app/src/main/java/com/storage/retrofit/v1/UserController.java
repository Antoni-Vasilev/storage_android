package com.storage.retrofit.v1;

import com.storage.model.TokenPublic;
import com.storage.model.UserLoginDto;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserController {

    @POST("/v1/user/login")
    Call<TokenPublic> login(@Body UserLoginDto userLoginDto);
}
