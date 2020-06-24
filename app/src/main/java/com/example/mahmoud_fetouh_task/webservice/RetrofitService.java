package com.example.mahmoud_fetouh_task.webservice;

import com.example.mahmoud_fetouh_task.webservice.response.ListResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;


public interface RetrofitService {
    @GET
    Call<ListResponse> getList(@Url String url);

}
