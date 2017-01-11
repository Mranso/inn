package com.inn.inn.util.imageloader;

import android.graphics.Bitmap;

import java.io.File;

public class InnImageLoaderConfiguration {

    private Bitmap.Config bitmapConfig;
    private int memoryCacheSize;
    private int diskCacheSize;
    private File diskCachePath;

    private InnImageLoaderConfiguration(Builder builder) {
        this.bitmapConfig = builder.bitmapConfig;
        this.diskCachePath = builder.diskCachePath;
        this.diskCacheSize = builder.diskCacheSize;
        this.memoryCacheSize = builder.memoryCacheSize;
    }

    public Bitmap.Config getBitmapConfig() {
        return bitmapConfig;
    }

    public int getMemoryCacheSize() {
        return memoryCacheSize;
    }

    public int getDiskCacheSize() {
        return diskCacheSize;
    }

    public File getDiskCachePath() {
        return diskCachePath;
    }

    public static class Builder {
        private Bitmap.Config bitmapConfig = Bitmap.Config.ARGB_8888;
        private int memoryCacheSize = 10;
        private int diskCacheSize = 40;
        private File diskCachePath;

        public Builder() {

        }

        public Builder bitmapConfig(Bitmap.Config config) {
            this.bitmapConfig = config;
            return this;
        }


        public Builder memoryCacheSize(int sizeInMB) {
            this.memoryCacheSize = sizeInMB * 1024 * 1024;
            return this;
        }

        public Builder diskCacheSize(int sizeInMB) {
            this.diskCacheSize = sizeInMB * 1024 * 1024;
            return this;
        }

        public Builder diskCachePath(File diskCachePath){
            this.diskCachePath = diskCachePath;
            return this;
        }

        public InnImageLoaderConfiguration build(){
            return new InnImageLoaderConfiguration(this);
        }

    }

}
