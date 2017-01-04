package com.inn.inn.network;

import com.inn.inn.BuildConfig;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class InnHttpClient {

    private static HttpService innHttpService = null;

    public static HttpService getHttpServiceInstance() {
        if (innHttpService == null) {
            synchronized (InnHttpClient.class) {
                if (innHttpService == null) {
                    innHttpService = createService(HttpService.class, InnApi.INN_BASE_API);
                }
            }
        }
        return innHttpService;
    }

    private static <T> T createService(Class<T> serviceClass, String baseUrl) {
        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
        clientBuilder.readTimeout(30, TimeUnit.SECONDS);
        clientBuilder.writeTimeout(20, TimeUnit.SECONDS);
        clientBuilder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request origin = chain.request();
                Request.Builder builder = origin.newBuilder();
                return chain.proceed(builder.build());
            }
        });
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            clientBuilder.addInterceptor(interceptor);
        }
        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(baseUrl)
                .client(clientBuilder.build())
                .build();
        return retrofit.create(serviceClass);
    }
}
