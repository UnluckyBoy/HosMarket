package com.cloudstudio.hosmarket.network.service;

import com.cloudstudio.hosmarket.entity.WebServerResponseBean;
import com.cloudstudio.hosmarket.entity.WebServerResponseOrderBean;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * @ClassName OrderService
 * @Author Create By matrix
 * @Date 2024/10/18 9:46
 */
public interface OrderService {
    @POST("createOrder")
    Call<WebServerResponseOrderBean> getState(
            @Query("order_uid") String order_uid,
            @Query("medicine_code") String medicine_code,
            @Query("medicine_batch_number") int medicine_batch_number,
            @Query("create_time") String create_time
    );
}
