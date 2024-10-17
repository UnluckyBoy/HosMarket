package com.cloudstudio.hosmarket.entity;

/**
 * @ClassName BannerDataInfo
 * @Author Create By matrix
 * @Date 2024/10/16 13:04
 * 轮播实体
 */
public class BannerDataInfo {
    private final int imageRes;
    private final String url;


    public BannerDataInfo(int mImageRes,String mUrl){
        imageRes=mImageRes;
        url=mUrl;
    }

    public int getImageRes() {
        return imageRes;
    }

    public String getUrl() {
        return url;
    }
}
