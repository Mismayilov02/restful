package com.example.restful;


import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface Retrofit_login {

    //TODO Replace with your API's Login Method
   @Headers({"Content-Type: text/plain; charset=utf-8"})
    @POST("login")
    Call<List<ResponseBody>> llogin(@Body login login);


    @Headers({"Content-Type: application/json; charset=utf-8"})
    @GET("MobileGetPriceM?ffilter=cp")
    Call<List<name_price>> name_prrice(@Header("Authorization") String Auzation);

    @Headers({"Content-Type: application/json; charset=utf-8"})
    @GET("GetOperationList?p_username=ATAMALI")
    Call<List<operation_list>> operation_list(@Header("Authorization") String Authorization  );


}
