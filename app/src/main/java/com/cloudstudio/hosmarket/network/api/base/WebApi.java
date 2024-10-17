package com.cloudstudio.hosmarket.network.api.base;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @ClassName WebApi
 * @Author Create By matrix
 * @Date 2024/10/17 15:51
 */
public abstract class WebApi {

    protected Retrofit getApi(String url) {
        /*使用拦截器获取token*/
        return new Retrofit.Builder()
                .baseUrl(url)
//                .client(new OkHttpClient.Builder()
//                        .addInterceptor(new TokenInterceptor())
//                        .build())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
    public abstract <T> T getService();
}
