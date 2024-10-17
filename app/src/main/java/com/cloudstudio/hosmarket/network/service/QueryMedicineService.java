package com.cloudstudio.hosmarket.network.service;

import com.cloudstudio.hosmarket.entity.WebServerResponseBean;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * @ClassName queryMedicineService
 * @Author Create By matrix
 * @Date 2024/10/17 15:54
 */
public interface QueryMedicineService {
    @POST("queryMedicineCodeInfoByCode")
    Call<WebServerResponseBean> getState(
            @Query("medicine_code") String medicine_code
    );
}
