package com.example.image_reader;

import com.google.api.client.auth.oauth2.StoredCredential;
import com.google.api.client.util.store.AbstractDataStore;
import com.google.api.client.util.store.DataStore;
import com.google.api.client.util.store.DataStoreFactory;

import java.io.IOException;
import java.util.Collection;
import java.util.Optional;
import java.util.Set;

import static java.util.stream.Collectors.toList;

public class JPADataStore extends AbstractDataStore<StoredCredential> {

    private GoogleCredentialDetailsRepository repository;
    private DataStoreFactory dataStoreFactory;

    protected JPADataStore(DataStoreFactory dataStoreFactory, String id, GoogleCredentialDetailsRepository repository) {
        super(dataStoreFactory, id);
        this.repository = repository;
    }

    @Override
    public DataStoreFactory getDataStoreFactory() {
        return dataStoreFactory;
    }

    @Override
    public Set<String> keySet() throws IOException {
        return repository.findAllIds();
    }

    @Override
    public Collection<StoredCredential> values() throws IOException {
        return repository.findAll().stream().map(c -> {
            StoredCredential credential = new StoredCredential();
            credential.setAccessToken(c.getAccessToken());
            credential.setRefreshToken(c.getRefreshToken());
            credential.setExpirationTimeMilliseconds(c.getExpirationTimeMilliseconds());
            return credential;
        }).collect(toList());
    }

    @Override
    public StoredCredential get(String key) throws IOException {
        Optional<GoogleCredential> jpaStoredCredentialOptional = repository.findById(key);
        if (!jpaStoredCredentialOptional.isPresent()) {
            return null;
        }
        GoogleCredential googleCredential = jpaStoredCredentialOptional.get();
        StoredCredential credential = new StoredCredential();
        credential.setAccessToken(googleCredential.getAccessToken());
        credential.setRefreshToken(googleCredential.getRefreshToken());
        credential.setExpirationTimeMilliseconds(googleCredential.getExpirationTimeMilliseconds());
        return credential;
    }

    @Override
    public DataStore<StoredCredential> set(String key, StoredCredential value) throws IOException {

        GoogleCredential googleCredential = repository.findById(key).orElse(new GoogleCredential(key, value));
        googleCredential.apply(value);
        repository.save(googleCredential);
        return this;
    }

    @Override
    public DataStore<StoredCredential> clear() throws IOException {
        repository.deleteAll();
        return this;
    }

    @Override
    public DataStore<StoredCredential> delete(String key) throws IOException {
        Optional<GoogleCredential> googleCredential = repository.findById(key);
        repository.delete(googleCredential.get());
        return this;
    }
}
