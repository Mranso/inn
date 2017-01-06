package com.inn.inn.network;

import com.inn.inn.firstpage.model.DayDetail;
import com.inn.inn.firstpage.model.TimeList;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Inn接口调用
 * */

public interface HttpService {

    @GET("day/history")
    Observable<TimeList> getTimeList();

    @GET("day/{time}")
    Observable<DayDetail> getDayList(@Path("time") String time);
}
