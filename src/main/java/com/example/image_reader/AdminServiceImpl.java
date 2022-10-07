package com.example.image_reader;


import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.services.drive.Drive;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private GoogleAuthorizationCodeFlow googleAuthorizationCodeFlow;



//    @Value("${google.credentials.url}")
    private String callBackURL = "http://localhost:8081/admin/savecode/";
    @Value("${Image.imageUrl}")
    private String imageUrl;



    @Override
    public String doGoogleSignIn() {
        return googleAuthorizationCodeFlow.newAuthorizationUrl().setRedirectUri(callBackURL).setAccessType("offline").build();
    }

    @Override
    public String saveAuthorizationCode(String code) throws IOException {
        GoogleTokenResponse tokenResponse = googleAuthorizationCodeFlow.newTokenRequest(code).setRedirectUri(callBackURL).execute();
        googleAuthorizationCodeFlow.createAndStoreCredential(tokenResponse, "bridgelabz");
        return "User Authenticated Successfully, Now you may close this tab";
    }


}
