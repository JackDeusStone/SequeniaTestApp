package com.sequeniatestapp.model.enitty.filmItem;

public abstract class FilmItem {

    public static final int TYPE_DATE = 0;
    public static final int TYPE_GENERAL = 1;

    abstract public int getType();
}
