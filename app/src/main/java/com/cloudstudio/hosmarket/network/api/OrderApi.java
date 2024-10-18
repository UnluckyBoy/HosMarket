package com.cloudstudio.hosmarket.network.api;

import com.cloudstudio.hosmarket.network.api.base.WebApi;
import com.cloudstudio.hosmarket.network.service.OrderService;
import com.cloudstudio.hosmarket.network.service.QueryMedicineService;

import retrofit2.Retrofit;

/**
 * @ClassName OrderApi
 * @Author Create By matrix
 * @Date 2024/10/18 9:46
 */
public class OrderApi extends WebApi {
    String url="https://2649b3f6.r20.cpolar.top/openApi/";
    Retrofit retrofit=getApi(url);
    @Override
    public <T> T getService() {
        return (T) retrofit.create(OrderService.class);
    }
}
