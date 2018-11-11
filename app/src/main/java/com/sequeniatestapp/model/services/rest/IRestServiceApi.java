package com.sequeniatestapp.model.services.rest;

import com.sequeniatestapp.model.services.rest.response.FilmsResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface IRestServiceApi {

    @GET("/sequeniatesttask/films.json")
    Observable<FilmsResponse> fetchFilms();

}
