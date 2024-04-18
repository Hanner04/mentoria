package com.clase.tarea.service;

import com.clase.tarea.model.Request;
import com.clase.tarea.model.Response;
import com.clase.tarea.model.UserInfo;
import com.clase.tarea.repository.IUserRepository;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final IUserRepository iUserRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthService.class);

    public ResponseEntity<Object> login(Request request) {

        try {
            LOGGER.info("INFO: Querying token: {} ", request);

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUser(), request.getPassword()));
            UserDetails userDetails = iUserRepository.findByUsername(request.getUser())
                    .orElse(null);

            if (userDetails != null) {
                return ResponseEntity.status(200)
                        .body(Response
                                .builder()
                                .code(200)
                                .message(jwtService.getToken(userDetails))
                                .build()
                        );
            }else {
                LOGGER.error("Authentication Error {}: ", "User not found");
                return ResponseEntity.status(401)
                        .body(Response
                                .builder()
                                .code(401)
                                .message("User not found")
                                .build()
                        );

            }
        }catch (AuthenticationException e){
            LOGGER.error("Authentication Error {}: ", e.getMessage());
            return ResponseEntity.status(401)
                    .body(Response
                            .builder()
                            .code(401)
                            .message("Authenticate error: " + e.getMessage())
                            .build()
                    );
        }
    }

    public ResponseEntity<Object> addUser(UserInfo userInfo){

        AtomicReference<String> message = new AtomicReference<>();
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<UserInfo>> violations = validator.validate(userInfo);

        try {

            if (!violations.isEmpty()) {
                violations.forEach(violation -> message.set(violation.getMessage()));
                LOGGER.error("Error: Create user: {} ", message);
                return ResponseEntity.status(401)
                        .body(Response
                                .builder()
                                .code(401)
                                .message(message.toString())
                                .build()
                        );
            }else{
                LOGGER.info("INFO: Create user: {}", userInfo);
                userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
                iUserRepository.save(userInfo);

                return ResponseEntity.status(200)
                        .body(Response
                                .builder()
                                .code(200)
                                .message("User added successfully")
                                .build()
                        );

            }
        }catch (Exception e){
            LOGGER.error("Error: Create user: {} ", "Duplicate key. This user is already registered.");
            return ResponseEntity.status(401)
                    .body(Response
                            .builder()
                            .code(401)
                            .message("Duplicate key. This user is registered. " + e.getMessage())
                            .build()
                    );
        }
    }

    public List<UserInfo> getAllUser(){
        return iUserRepository.findAll();
    }

}
