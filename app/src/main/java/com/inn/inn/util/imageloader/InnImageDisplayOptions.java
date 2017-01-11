package com.inn.inn.util.imageloader;

import android.graphics.Bitmap;

public class InnImageDisplayOptions {
    private int imageOnFail;
    private int imageOnLoading;
    private int placeHolderImage;
    private boolean cacheInMemory;
    private boolean cacheInDisk;
    private Bitmap.Config bitmapConfig;

    private InnScaleTypeUtil.ScaleTypeWrapper scaleType;

    public int getImageOnFail() {
        return imageOnFail;
    }

    public int getImageOnLoading() {
        return imageOnLoading;
    }

    public int getPlaceHolderImage() {
        return placeHolderImage;
    }

    public boolean isCacheInMemory() {
        return cacheInMemory;
    }

    public boolean isCacheInDisk() {
        return cacheInDisk;
    }

    public Bitmap.Config getBitmapConfig() {
        return bitmapConfig;
    }

    public InnScaleTypeUtil.ScaleTypeWrapper getScaleType() {
        return scaleType;
    }

    private InnImageDisplayOptions(Builder builder){
        this.imageOnFail = builder.imageOnFailed;
        this.imageOnLoading = builder.imageOnLoading;
        this.placeHolderImage = builder.placeHolderImage;
        this.cacheInMemory = builder.cacheInMemory;
        this.cacheInDisk = builder.cacheInDisk;
        this.bitmapConfig = builder.bitmapConfig;
    }

    public static class Builder{
        private int imageOnFailed = 0;
        private int imageOnLoading = 0;
        private int placeHolderImage = 0;
        private boolean cacheInMemory = true;
        private boolean cacheInDisk = true;
        private Bitmap.Config bitmapConfig = Bitmap.Config.ARGB_8888;

        public Builder(){

        }

        public Builder showImageOnFail(int resId){
            this.imageOnFailed = resId;
            return this;
        }

        public Builder showImageOnLoading(int resId){
            this.imageOnLoading = resId;
            return this;
        }

        public Builder showPlaceHolderImage(int resId){
            this.placeHolderImage  = resId;
            return this;
        }

        public Builder cacheInMemory(boolean cacheInMemory){
            this.cacheInMemory = cacheInMemory;
            return this;
        }

        public Builder cacheInDisk(boolean cacheInDisk){
            this.cacheInDisk = cacheInDisk;
            return this;
        }

        private Builder bitmapConfig(Bitmap.Config bitmapConfig){
            this.bitmapConfig = bitmapConfig;
            return this;
        }

        public InnImageDisplayOptions build(){
            return new InnImageDisplayOptions(this);
        }

    }
}
