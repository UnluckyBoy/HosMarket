package com.cloudstudio.hosmarket.network.service;

import com.cloudstudio.hosmarket.entity.WebServerResponseBean;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * @ClassName OutWareHouseService
 * @Author Create By matrix
 * @Date 2024/10/17 20:43
 */
public interface OutWareHouseService {
    @POST("medicineOutWareHouse")
    Call<WebServerResponseBean> getState(
            @Query("medicine_code") String medicine_code,
            @Query("medicine_batch_number") int medicine_batch_number,
            @Query("outCount") int outCount,
            @Query("create_time") String create_time
    );
}
