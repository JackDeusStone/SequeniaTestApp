package com.sequeniatestapp.model.modelManager.helper;

import com.sequeniatestapp.model.enitty.Film;
import com.sequeniatestapp.model.enitty.database.FilmTable;

import java.util.Objects;

public class FilmsRealmHelper {

    public static FilmTable makeFilmTableFromFilm(Film film){
        if (film != null){
            FilmTable filmTable = new FilmTable();

            filmTable.setId(film.getId());
            filmTable.setLocalizedName(film.getLocalizedName());
            filmTable.setName(film.getName());
            filmTable.setRating(film.getRating());
            filmTable.setYear(film.getYear());
            filmTable.setImageUrl(film.getImageUrl());
            filmTable.setDescription(film.getDescription());

            return filmTable;
        }else {
            return null;
        }
    }

    public static Film makeFilmFromFilmTable(FilmTable filmTable){

        Film film = new Film();

        film.setId(filmTable.getId());
        film.setLocalizedName(filmTable.getLocalizedName());
        film.setName(filmTable.getName());
        film.setRating(filmTable.getRating());
        film.setYear(filmTable.getYear());
        film.setImageUrl(filmTable.getImageUrl());
        film.setDescription(filmTable.getDescription());

        return film;
    }

    public static boolean isFilmTableEqualsFilm(FilmTable filmTable, Film film){

        if (filmTable == null && film == null)
            return false;

        if (!Objects.equals(film.getId(), filmTable.getId())){
            return false;
        }

        if (!Objects.equals(film.getLocalizedName(), filmTable.getLocalizedName())){
            return false;
        }

        if (!Objects.equals(film.getName(), filmTable.getName())){
            return false;
        }

        if (!Objects.equals(film.getYear(), filmTable.getYear())){
            return false;
        }

        if (!Objects.equals(film.getRating(), filmTable.getRating())){
            return false;
        }

        if (!Objects.equals(film.getImageUrl(), filmTable.getImageUrl())){
            return false;
        }

        if (!Objects.equals(film.getDescription(), filmTable.getDescription())){
            return false;
        }

        return true;
    }
}
