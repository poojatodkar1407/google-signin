package com.example.image_reader;


import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/admin")
public class CodinClubAdminController {

    @Autowired
    public AdminServiceImpl adminService;



    @GetMapping("/codinclub/google-signin")
    @ApiOperation("API to get authentication redirect URL")
    @ApiResponses({@ApiResponse(
            code = 200,
            message = "Authenticated Redirect URl",
            response = Response.class
    )})
    public ResponseEntity<Response> doGoogleSignIn() {
        String redirectURL = adminService.doGoogleSignIn();
        Map<String, String> url = new HashMap<>();
        url.put("redirectURL", redirectURL);
        return new ResponseEntity<>(new Response<>("data", url), HttpStatus.OK);
    }

    @GetMapping("/savecode")
    @ApiOperation("API to get authorized the user")
    public String saveAuthorizationCode(HttpServletRequest request) throws IOException {
        String code = request.getParameter("code");
        if (code != null) {
            return adminService.saveAuthorizationCode(code);
        }
        return code;
    }

}
