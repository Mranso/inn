package com.inn.inn.network;

import com.inn.inn.firstpage.model.TimeList;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Inn接口调用
 * */

public interface HttpService {

    @GET("day/history")
    Observable<TimeList> getTimeList();
}
