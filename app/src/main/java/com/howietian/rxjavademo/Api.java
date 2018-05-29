package com.howietian.rxjavademo;

import android.support.annotation.Nullable;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

/**
 * Created by 83624 on 2018/3/15 0015.
 */

public interface Api {
    @GET("/hello/say")
    Observable<Integer> getInteger( @Query("id") Integer id);

    @GET("/hello/say")
    Observable<Integer> getInteger();

    @GET("/api/v1/extract")
    Observable<Result> getClassifyResult(@Query("url") String url);

//根据 MActivity 的id查询评论
    @GET("/1/classes/Comment")
    @Headers({
            "X-Bmob-Application-Id: 7364fe515ee1f8b94b89cd818924ddaa",
            "X-Bmob-REST-API-Key: 2b4c374007e7a9c54b5156a49e259369",
            "Content-Type: application/json"
    })
    Observable<String> getComment();
}
