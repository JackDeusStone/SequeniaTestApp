package com.sequeniatestapp.model.modelManager;

import com.sequeniatestapp.model.enitty.Film;
import com.sequeniatestapp.model.modelManager.helper.FilmsRestHelper;
import com.sequeniatestapp.model.services.rest.RestService;
import com.sequeniatestapp.model.services.rest.response.FilmObject;
import com.sequeniatestapp.model.services.rest.response.FilmsResponse;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

class FilmsRestModule {

    public interface FilmRestModuleComletionHandler{
        void onSuccess(List<Film> films);
        void onError();
    }

    private RestService mRestService;

    public FilmsRestModule(RestService restService){
        mRestService = restService;
    }

    public void fetchFilms(FilmRestModuleComletionHandler comletionHandler){

        mRestService.getRestServiceApi().fetchFilms()
                .flatMap((Function<FilmsResponse, ObservableSource<List<Film>>>) filmsResponse -> {

                    List<Film> films = new ArrayList<>();

                    if (filmsResponse != null && filmsResponse.getFilms() != null){
                        for (FilmObject filmObject : filmsResponse.getFilms()){
                            films.add(FilmsRestHelper.makeFilmFromFilmObject(filmObject));
                        }
                    }

                    return Observable.just(films);
                })
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(films -> {
                    if (comletionHandler != null){
                        comletionHandler.onSuccess(films);
                    }
                })
                .doOnError(throwable -> {
                    if (comletionHandler != null)
                        comletionHandler.onError();
                })
                .subscribe(new Observer<List<Film>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<Film> films) {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
