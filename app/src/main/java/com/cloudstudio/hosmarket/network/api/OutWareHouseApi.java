package com.cloudstudio.hosmarket.network.api;

import com.cloudstudio.hosmarket.network.api.base.WebApi;
import com.cloudstudio.hosmarket.network.service.AddWareHouseService;
import com.cloudstudio.hosmarket.network.service.OutWareHouseService;

import retrofit2.Retrofit;

/**
 * @ClassName OutWareHouseApi
 * @Author Create By matrix
 * @Date 2024/10/17 20:42
 */
public class OutWareHouseApi  extends WebApi {
    String url="https://2649b3f6.r20.cpolar.top/openApi/";
    Retrofit retrofit=getApi(url);
    @Override
    public <T> T getService() {
        return (T) retrofit.create(OutWareHouseService.class);
    }
}
