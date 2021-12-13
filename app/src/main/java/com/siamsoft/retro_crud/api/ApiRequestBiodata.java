package com.siamsoft.retro_crud.api;

import com.siamsoft.retro_crud.model.ResponseModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiRequestBiodata {


    @FormUrlEncoded
    @POST("insert.php")
    Call<ResponseModel> sendBiodata(@Field("name") String name, @Field("salary") String salary, @Field("designation") String designation,@Field("address") String address);

}
