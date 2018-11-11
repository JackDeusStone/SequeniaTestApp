package com.sequeniatestapp.model.enitty.filmItem;

public class DateFilmItem extends FilmItem{

    private String date;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public int getType() {
        return TYPE_DATE;
    }
}
