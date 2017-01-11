package com.inn.inn.util.imageloader;

import android.view.View;

public interface InnImageLoadingListener {
    void onLoadingStarted(String imageUri, View view);

    void onLoadingFailed(String imageUri, View view, Throwable error);

    void onLoadingComplete(String imageUri, View view);

    void onLoadingCancelled(String imageUri, View view);
}
