package com.github.belwals.security.auth;

import com.github.belwals.security.exception.BadRequestException;
import com.github.belwals.security.exception.DomainError;
import com.github.belwals.security.exception.ErrorObject;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
@CrossOrigin
public class AuthenticationController {
    private final AuthenticationService service;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<?> register(
            @Valid @RequestBody RegistrationRequest request) throws Exception {
        try {
            service.register(request);
        } catch (Exception e) {
            return buildErrorResponse(e);
        }
        return ResponseEntity.accepted().build();
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authentication(
            @Valid @RequestBody AuthenticationRequest request) throws Exception {
        var resp = service.authenticate(request);
        return ResponseEntity.ok(resp);
    }

    private ResponseEntity<Object> buildErrorResponse(Exception e) {

        if (e instanceof BadRequestException) {
            ErrorObject eo = new ErrorObject(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(eo);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorObject("internal server error"));
    }

}
