package com.sequeniatestapp.model.modelManager.helper;

import com.sequeniatestapp.model.enitty.Film;
import com.sequeniatestapp.model.services.rest.response.FilmObject;

public class FilmsRestHelper {

    public static Film makeFilmFromFilmObject(FilmObject filmObject){
        if (filmObject != null){
            Film film = new Film();
            film.setId(filmObject.getId());
            film.setLocalizedName(filmObject.getLocalizedName());
            film.setName(filmObject.getName());
            film.setYear(filmObject.getYear());
            film.setRating(filmObject.getRating());
            film.setImageUrl(filmObject.getImageUrl());
            film.setDescription(filmObject.getDescription());

            return film;
        } else {
          return null;
        }
    }
}
