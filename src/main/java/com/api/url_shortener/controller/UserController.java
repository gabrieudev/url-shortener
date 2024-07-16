package com.api.url_shortener.controller;

import com.api.url_shortener.controller.dto.PasswordDTO;
import com.api.url_shortener.controller.dto.UserDTO;
import com.api.url_shortener.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/confirm")
    public ResponseEntity<String> confirm(@RequestParam("token") String token) {
        userService.confirmEmail(token);
        return ResponseEntity.status(HttpStatus.OK).body("Email confirmed successfully");
    }

    @GetMapping
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<List<UserDTO>> getAll(Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getAll(pageable).getContent());
    }

    @GetMapping("/{userId}")
    @PreAuthorize("hasAuthority('SCOPE_BASIC')")
    public ResponseEntity<UserDTO> getById(@PathVariable("userId") UUID userId) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getById(userId));
    }

    @DeleteMapping("/{userId}")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<String> delete(@PathVariable("userId") UUID userId) {
        userService.delete(userId);
        return ResponseEntity.status(HttpStatus.OK).body("User removed successfully");
    }

    @PostMapping("/change-password")
    @PreAuthorize("hasAuthority('SCOPE_BASIC')")
    public ResponseEntity<String> changePassword(@Valid @RequestBody PasswordDTO passwordDTO) {
        userService.changePassword(passwordDTO);
        return ResponseEntity.status(HttpStatus.OK).body("Password changed successfully");
    }

}
