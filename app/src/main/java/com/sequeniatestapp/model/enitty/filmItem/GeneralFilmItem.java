package com.sequeniatestapp.model.enitty.filmItem;

import com.sequeniatestapp.model.enitty.Film;

public class GeneralFilmItem extends FilmItem{

    private Film mFilm;

    public void setFilm(Film film) {
        mFilm = film;
    }

    public Film getFilm() {
        return mFilm;
    }

    @Override
    public int getType() {
        return TYPE_GENERAL;
    }
}
