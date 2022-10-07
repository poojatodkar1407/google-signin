package com.example.image_reader;

import java.io.IOException;
import java.security.GeneralSecurityException;



public interface AdminService {

    String doGoogleSignIn() throws GeneralSecurityException, IOException;

    String saveAuthorizationCode(String code) throws IOException;


}
