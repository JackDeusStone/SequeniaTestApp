
package com.sequeniatestapp.model.services.rest.response;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FilmsResponse {

    @SerializedName("films")
    @Expose
    private List<FilmObject> films = null;

    public List<FilmObject> getFilms() {
        return films;
    }

    public void setFilms(List<FilmObject> films) {
        this.films = films;
    }

}
