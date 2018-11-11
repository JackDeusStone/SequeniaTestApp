package com.sequeniatestapp.model.services.rest;

import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestService {

    private static final String BASE_URL = "https://s3-eu-west-1.amazonaws.com";
    private Retrofit mRetrofit;
    private IRestServiceApi mRestServiceApi;

    public RestService(){

        RxJava2CallAdapterFactory rxAdapter = RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io());

        HttpLoggingInterceptor interceptorBody = new HttpLoggingInterceptor();
        interceptorBody.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptorBody)
                .build();

        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addCallAdapterFactory(rxAdapter)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mRestServiceApi = mRetrofit.create(IRestServiceApi.class);
    }

    public IRestServiceApi getRestServiceApi() {
        return mRestServiceApi;
    }
}
