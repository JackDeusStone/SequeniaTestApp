package com.sequeniatestapp.model.services;

import com.sequeniatestapp.model.modelManager.FilmsManager;
import com.sequeniatestapp.model.services.rest.RestService;
import com.sequeniatestapp.model.services.storage.StorageService;

public class Service {

    private static Service mInstance;

    public static Service getInstance(){
        if (mInstance == null){
            mInstance = new Service();
        }

        return mInstance;
    }

    private RestService mRestService;
    private StorageService mStorageService;

    private FilmsManager mFilmsManager;

    public Service(){

        mRestService = new RestService();
        mStorageService = new StorageService();

        mFilmsManager = new FilmsManager(mRestService, mStorageService);

    }

    public FilmsManager getFilmsManager() {
        return mFilmsManager;
    }
}
