package com.example.image_reader;

import com.google.api.client.util.store.DataStoreFactory;

import java.io.IOException;

public class JPADataStoreFactory implements DataStoreFactory {

    private GoogleCredentialDetailsRepository repository;

    public JPADataStoreFactory(GoogleCredentialDetailsRepository repository) {
        this.repository = repository;
    }

    @SuppressWarnings("unchecked")
    @Override
    public JPADataStore getDataStore(String id) throws IOException {
        return new JPADataStore(this, id, repository);
    }
}
