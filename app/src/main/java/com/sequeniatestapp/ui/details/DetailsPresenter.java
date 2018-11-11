package com.sequeniatestapp.ui.details;

import com.sequeniatestapp.model.enitty.Film;
import com.sequeniatestapp.model.services.Service;

public class DetailsPresenter {

    private DetailsFilmContract.View mView;

    public DetailsPresenter(DetailsFilmContract.View view){
        mView = view;
    }

    public void loadDataByFilmId(int filmId){
       Film film =  Service.getInstance().getFilmsManager().getFilmById(filmId);
       mView.updateUI(film);
    }
}
