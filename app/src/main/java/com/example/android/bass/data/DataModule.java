package com.example.android.bass.data;

import android.content.Context;

import com.example.android.bass.BuildConfig;
import com.example.android.bass.data.bus.RxBus;
import com.squareup.okhttp.Cache;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.picasso.OkHttpDownloader;
import com.squareup.picasso.Picasso;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DataModule {

    private static final int DISK_CACHE_SIZE = 50 * 1024 * 1024; // 10 MB

    private final Context context;

    public DataModule(Context context) {
        this.context = context;
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient() {
        final OkHttpClient client = new OkHttpClient();

        // set disk cache
        client.setCache(new Cache(context.getCacheDir(), DISK_CACHE_SIZE));

        if (BuildConfig.DEBUG) {
            // stetho interceptor
            StethoUtil.addStethoInterceptor(client);
        }

        return client;
    }

    @Provides
    @Singleton
    Picasso providePicasso(OkHttpClient okHttpClient) {
        return new Picasso.Builder(context)
                .downloader(new OkHttpDownloader(okHttpClient))
                .build();
    }

    @Provides
    @Singleton
    RxBus provideRxBus() {
        return new RxBus();
    }

}
