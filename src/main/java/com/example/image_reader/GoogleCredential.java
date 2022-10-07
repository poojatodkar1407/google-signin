package com.example.image_reader;

import com.google.api.client.auth.oauth2.StoredCredential;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.Instant;

@Entity(name = "google_credentials")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class GoogleCredential implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    private String id;
    private String accessToken;
    private Long expirationTimeMilliseconds;
    private String refreshToken;

    @CreatedDate
    private Instant createdAt;
    @LastModifiedDate
    private Instant updatedAt;

    public GoogleCredential(String key, StoredCredential credential) {
        this.id = key;
        this.accessToken = credential.getAccessToken();
        this.expirationTimeMilliseconds = credential.getExpirationTimeMilliseconds();
        this.refreshToken = credential.getRefreshToken();
        this.createdAt = Instant.now();
        this.updatedAt = Instant.now();
    }

    public void apply(StoredCredential credential) {
        this.accessToken = credential.getAccessToken();
        this.expirationTimeMilliseconds = credential.getExpirationTimeMilliseconds();
        if (credential.getRefreshToken() != null) {
            this.refreshToken = credential.getRefreshToken();
        }
        this.updatedAt = Instant.now();
    }

}
