package com.sequeniatestapp.ui.films;

import com.sequeniatestapp.model.enitty.Film;
import com.sequeniatestapp.model.modelEnum.RequestStatus;

import java.util.List;

public interface FilmsContract {

    interface View{

        void onProgressBarStatus(RequestStatus requestStatus);
        void makeErrorToast();
        void onRefreshLIst(List<Film> films);

    }

}
