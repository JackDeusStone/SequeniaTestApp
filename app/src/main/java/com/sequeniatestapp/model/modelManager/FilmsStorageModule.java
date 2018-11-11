package com.sequeniatestapp.model.modelManager;

import com.sequeniatestapp.model.enitty.Film;
import com.sequeniatestapp.model.enitty.database.FilmTable;
import com.sequeniatestapp.model.modelManager.helper.FilmsRealmHelper;
import com.sequeniatestapp.model.services.storage.StorageService;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

class FilmsStorageModule {

    public interface FileStorageModuleCompletionHandler{
        void onSuccess();
        void onError();
    }

    private Realm mRealm;

    public FilmsStorageModule(StorageService storageService){
        mRealm = storageService.getRealm();
    }

    public void writeFilmsToRealm(List<Film> films, FileStorageModuleCompletionHandler completionHandler){

        mRealm.executeTransactionAsync(realm -> {
            for (Film film : films){
                if (film != null){
                    FilmTable filmTable = realm
                            .where(FilmTable.class)
                            .equalTo("id", film.getId())
                            .findFirst();

                    if (filmTable != null){

                        if (!FilmsRealmHelper.isFilmTableEqualsFilm(filmTable, film)){
                            filmTable = FilmsRealmHelper.makeFilmTableFromFilm(film);
                        }

                    }else {
                        filmTable = FilmsRealmHelper.makeFilmTableFromFilm(film);
                    }

                    realm.copyToRealmOrUpdate(filmTable);
                }
            }
        }, () -> {
            if (completionHandler != null)
                completionHandler.onSuccess();
        });
    }

    public Film getFilmById(int id){

        FilmTable filmTable = mRealm
                .where(FilmTable.class)
                .equalTo("id", id)
                .findFirst();

        if (filmTable != null){
            return FilmsRealmHelper.makeFilmFromFilmTable(filmTable);
        }else {
            return null;
        }
    }

    public List<Film> getFilms(){
        RealmResults<FilmTable> results = mRealm.where(FilmTable.class).findAll();
        List<Film> films = new ArrayList<>();

        for (FilmTable filmTable : results){
            films.add(FilmsRealmHelper.makeFilmFromFilmTable(filmTable));
        }

        return films;
    }
}
