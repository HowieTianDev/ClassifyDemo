package com.howietian.rxjavademo;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by 83624 on 2018/3/15 0015.
 */

public class Utils {
    //分类API
    public static Retrofit create(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://ci.lab317.org")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return retrofit;
    }

//    public static Retrofit createBmobRetrofit(){
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("https://api.bmob.cn")
//                .addConverterFactory(GsonConverterFactory.create())
//                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                .build();
//
//        return retrofit;
//    }
}
