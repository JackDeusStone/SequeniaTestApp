package com.sequeniatestapp.ui.details;

import com.sequeniatestapp.model.enitty.Film;

public interface DetailsFilmContract {

    interface View{

        void updateUI(Film film);

    }
}
