package com.sequeniatestapp.model.services.storage;

import io.realm.Realm;

public class StorageService {

    private Realm mRealm;

    public StorageService(){
        mRealm = Realm.getDefaultInstance();
    }

    public Realm getRealm() {
        return mRealm;
    }
}
