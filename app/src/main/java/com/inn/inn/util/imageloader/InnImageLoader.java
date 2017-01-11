package com.inn.inn.util.imageloader;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import java.io.File;

public class InnImageLoader {
    private static InnImageLoader instance;

    private InnImageLoader() {

    }

    public static InnImageLoader getInstance() {
        if (instance == null) {
            synchronized (InnImageLoader.class) {
                if (instance == null) {
                    instance = new InnImageLoader();
                }
            }
        }
        return instance;
    }

    public void init(Context context) {
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .diskCache(new UnlimitedDiskCache(context.getCacheDir()))
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .memoryCacheSizePercentage(10)
                .build();
        ImageLoader.getInstance().init(config);
    }

    public void init(Context context, final InnImageLoaderConfiguration configuration) {
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .diskCache(new UnlimitedDiskCache(configuration.getDiskCachePath()))
                .memoryCacheSize(configuration.getMemoryCacheSize())
                .diskCacheSize(configuration.getDiskCacheSize())
                .build();
        ImageLoader.getInstance().init(config);
    }

    public void displayImage(ImageView imageView, String uri) {
        displayImage(imageView, uri, null, null);
    }

    public void displayImage(ImageView imageView, String uri, InnImageDisplayOptions options) {
        displayImage(imageView, uri, options, null);
    }

    public void displayImage(ImageView imageView, String uri, InnImageLoadingListener listener) {
        displayImage(imageView, uri, null, listener);
    }

    public void displayImage(final View imageView, final String uri, InnImageDisplayOptions options, final InnImageLoadingListener listener) {
        if (!(imageView instanceof ImageView)) {
            throw new IllegalArgumentException("view should be imageview");
        }
        DisplayImageOptions displayImageOptions = DisplayImageOptions.createSimple();
        if (options != null) {
            displayImageOptions = new DisplayImageOptions.Builder()
                    .showImageOnFail(options.getImageOnFail())
                    .showImageOnLoading(options.getImageOnLoading())
                    .bitmapConfig(options.getBitmapConfig())
                    .cacheInMemory(options.isCacheInMemory())
                    .cacheOnDisk(options.isCacheInDisk())
                    .considerExifParams(true)
                    .bitmapConfig(Bitmap.Config.RGB_565)
                    .imageScaleType(ImageScaleType.EXACTLY)
                    .showImageForEmptyUri(options.getPlaceHolderImage())
                    .build();
        }
        ImageLoadingListener imageLoadingListener = new SimpleImageLoadingListener();
        if (listener != null) {
            imageLoadingListener = new ImageLoadingListener() {
                @Override
                public void onLoadingStarted(String imageUri, View view) {
                    listener.onLoadingStarted(imageUri, view);
                }

                @Override
                public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                    listener.onLoadingFailed(imageUri, view, new Throwable(failReason.toString()));
                }

                @Override
                public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                    listener.onLoadingComplete(imageUri, view);
                }

                @Override
                public void onLoadingCancelled(String imageUri, View view) {
                    listener.onLoadingCancelled(imageUri, view);
                }
            };
        }
        ImageView target = (ImageView) imageView;
        ImageLoader.getInstance().displayImage(uri, target, displayImageOptions, imageLoadingListener);

    }

    public File getCacheFromDisk(String uri) {
        return ImageLoader.getInstance().getDiskCache().get(uri);
    }

    public String getCachePathFromDisk(String uri) {
        File file = getCacheFromDisk(uri);
        if (file != null) {
            return file.getAbsolutePath();
        }
        return "";
    }

    public Bitmap getBitmapFromMemoryCache(String uri) {
        return ImageLoader.getInstance().getMemoryCache().get(uri);
    }

    public void pause() {
        ImageLoader.getInstance().pause();
    }

    public void resume() {
        ImageLoader.getInstance().resume();
    }
}
