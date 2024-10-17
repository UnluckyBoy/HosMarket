package com.cloudstudio.hosmarket.network.api;

import com.cloudstudio.hosmarket.network.api.base.WebApi;
import com.cloudstudio.hosmarket.network.service.AddWareHouseService;

import retrofit2.Retrofit;

/**
 * @ClassName AddWareHouseApi
 * @Author Create By matrix
 * @Date 2024/10/17 18:53
 */
public class AddWareHouseApi extends WebApi {
    String url="https://2649b3f6.r20.cpolar.top/openApi/";
    Retrofit retrofit=getApi(url);
    @Override
    public <T> T getService() {
        return (T) retrofit.create(AddWareHouseService.class);
    }
}
