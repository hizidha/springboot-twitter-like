package com.devland.assignment.assignment12.authentication;

import com.devland.assignment.assignment12.applicationuser.ApplicationUserService;
import com.devland.assignment.assignment12.applicationuser.model.ApplicationUser;
import com.devland.assignment.assignment12.authentication.jwt.JwtProvider;
import com.devland.assignment.assignment12.authentication.model.dto.JwtResponseDTO;
import com.devland.assignment.assignment12.authentication.model.dto.LoginRequestDTO;
import com.devland.assignment.assignment12.authentication.model.dto.RegistrationRequestDTO;
import com.devland.assignment.assignment12.fileupload.FileUploadService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@AllArgsConstructor
@RequestMapping("/auth")
public class AuthenticationController {
    private static final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);
    private final AuthenticationManager authenticationManager;
    private final ApplicationUserService applicationUserService;
    private final FileUploadService fileUploadService;
    private final JwtProvider jwtProvider;

    @PostMapping("/login")
    public ResponseEntity<JwtResponseDTO> login(
            @RequestBody LoginRequestDTO loginRequestDTO
    ) {
        logger.info("Attempt login to system");
        Authentication authentication = this.authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequestDTO.getUsername(),
                                                                      loginRequestDTO.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = this.jwtProvider.generateJwtToken(authentication);

        return ResponseEntity.ok(new JwtResponseDTO(jwt));
    }

    @PostMapping(value = "/register")
    public ResponseEntity<JwtResponseDTO> register(
            @RequestPart("fileImage") MultipartFile file,
            @RequestPart("registrationRequestDTO") @Valid RegistrationRequestDTO registrationRequestDTO
    ) {
        ApplicationUser newUser = registrationRequestDTO.convertToEntity();
        if (file != null && !file.isEmpty()) {
            String nameFile = this.fileUploadService.savedPhoto(newUser, file);
            newUser.setProfilePicturePath(nameFile);
        }
        this.applicationUserService.save(newUser);

        Authentication authentication = this.authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(registrationRequestDTO.getUsername(),
                        registrationRequestDTO.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = this.jwtProvider.generateJwtToken(authentication);
        return ResponseEntity.status(HttpStatus.CREATED).body(new JwtResponseDTO(jwt));
    }
}