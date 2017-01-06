package com.inn.inn.firstpage.model;

import java.io.Serializable;
import java.util.List;


public class DataTypeResult implements Serializable{
    private List<DayBaseData> Android ;

    private List<DayBaseData> iOS ;

    private List<DayBaseData> 休息视频 ;

    private List<DayBaseData> 拓展资源 ;

    private List<DayBaseData> 福利 ;

    private List<DayBaseData> 瞎推荐 ;

    private List<DayBaseData> 前端 ;

    private List<DayBaseData> App ;

    private List<DayBaseData> all ;

    public List<DayBaseData> getAndroid() {
        return Android;
    }

    public void setAndroid(List<DayBaseData> android) {
        Android = android;
    }

    public List<DayBaseData> getiOS() {
        return iOS;
    }

    public void setiOS(List<DayBaseData> iOS) {
        this.iOS = iOS;
    }

    public List<DayBaseData> get休息视频() {
        return 休息视频;
    }

    public void set休息视频(List<DayBaseData> 休息视频) {
        this.休息视频 = 休息视频;
    }

    public List<DayBaseData> get拓展资源() {
        return 拓展资源;
    }

    public void set拓展资源(List<DayBaseData> 拓展资源) {
        this.拓展资源 = 拓展资源;
    }

    public List<DayBaseData> get福利() {
        return 福利;
    }

    public void set福利(List<DayBaseData> 福利) {
        this.福利 = 福利;
    }

    public List<DayBaseData> get瞎推荐() {
        return 瞎推荐;
    }

    public void set瞎推荐(List<DayBaseData> 瞎推荐) {
        this.瞎推荐 = 瞎推荐;
    }

    public List<DayBaseData> get前端() {
        return 前端;
    }

    public void set前端(List<DayBaseData> 前端) {
        this.前端 = 前端;
    }

    public List<DayBaseData> getApp() {
        return App;
    }

    public void setApp(List<DayBaseData> app) {
        App = app;
    }

    public List<DayBaseData> getAll() {
        return all;
    }

    public void setAll(List<DayBaseData> all) {
        this.all = all;
    }
}
