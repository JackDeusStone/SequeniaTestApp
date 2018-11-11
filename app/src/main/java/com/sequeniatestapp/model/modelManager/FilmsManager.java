package com.sequeniatestapp.model.modelManager;

import com.sequeniatestapp.model.enitty.Film;
import com.sequeniatestapp.model.services.rest.RestService;
import com.sequeniatestapp.model.services.storage.StorageService;

import java.util.List;

public class FilmsManager {

    public interface FilmsManagerCompletionHandler {
        void onSuccess();
        void onError();
    }

    private FilmsRestModule mFilmsRestModule;
    private FilmsStorageModule mFilmsStorageModule;

    public FilmsManager(RestService restService, StorageService storageService){

        mFilmsRestModule = new FilmsRestModule(restService);
        mFilmsStorageModule = new FilmsStorageModule(storageService);

    }


    public void fetchFilms(FilmsManagerCompletionHandler completionHandler){

        mFilmsRestModule.fetchFilms(new FilmsRestModule.FilmRestModuleComletionHandler() {
            @Override
            public void onSuccess(List<Film> films) {
                mFilmsStorageModule.writeFilmsToRealm(films, new FilmsStorageModule.FileStorageModuleCompletionHandler() {
                    @Override
                    public void onSuccess() {
                        if (completionHandler != null)
                            completionHandler.onSuccess();
                    }

                    @Override
                    public void onError() {

                    }
                });
            }

            @Override
            public void onError() {
                if (completionHandler != null)
                    completionHandler.onError();
            }
        });
    }

    public Film getFilmById(int id){
        return mFilmsStorageModule.getFilmById(id);
    }

    public List<Film> getFilms(){
        return mFilmsStorageModule.getFilms();
    }
}
