package com.example.android.bass.data.api;

import retrofit.http.GET;
import rx.Observable;
import com.example.android.bass.data.model.FiveHundredPxSearchResult;

public interface FiveHundredPxApi {

    @GET("/v1/photos?feature=popular&sort=rating&image_size=5&rpp=40")
    Observable<FiveHundredPxSearchResult> mostPopular();

}
