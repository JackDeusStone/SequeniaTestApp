package com.sequeniatestapp.ui.films;

import com.sequeniatestapp.model.modelEnum.RequestStatus;
import com.sequeniatestapp.model.modelManager.FilmsManager;
import com.sequeniatestapp.model.services.Service;

public class FilmsPresenter {

    private FilmsContract.View mView;

    public FilmsPresenter(FilmsContract.View view){
        mView = view;
    }

    public void fetchFilms(){
        mView.onProgressBarStatus(RequestStatus.START_DOWNLOAD);
        Service.getInstance().getFilmsManager().fetchFilms(new FilmsManager.FilmsManagerCompletionHandler() {
            @Override
            public void onSuccess() {
                mView.onProgressBarStatus(RequestStatus.END_DOWNLOAD);
                mView.onRefreshLIst(Service.getInstance().getFilmsManager().getFilms());
            }

            @Override
            public void onError() {
                mView.onProgressBarStatus(RequestStatus.END_DOWNLOAD);
                mView.makeErrorToast();
            }
        });
    }
}
