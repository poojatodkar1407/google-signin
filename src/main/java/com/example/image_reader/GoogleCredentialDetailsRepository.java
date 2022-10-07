package com.example.image_reader;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.Set;

public interface GoogleCredentialDetailsRepository extends JpaRepository<GoogleCredential, Long> {

    Optional<GoogleCredential> findById(String key);

    Optional<GoogleCredential> findByAccessToken(String key);

    @Query(value = "select key from google_credential", nativeQuery = true)
    Set<String> findAllIds();

}
