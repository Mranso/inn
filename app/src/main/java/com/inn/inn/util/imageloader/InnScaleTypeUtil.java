package com.inn.inn.util.imageloader;

import com.nostra13.universalimageloader.core.assist.ImageScaleType;

public class InnScaleTypeUtil {

    public static class ScaleTypeWrapper{
        private ImageScaleType scaleType;
        public ScaleTypeWrapper(ImageScaleType scaleType){
            this.scaleType = scaleType;
        }
        public ImageScaleType getScaleType(){
            return scaleType;
        }
    }
}
