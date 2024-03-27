package com.example.notiumb.security.service;

import com.example.notiumb.model.User;

public interface IJwtService {
    String extractUserName(String token);

    String generateToken(User usuario);

    boolean isTokenValid(String token, User usuario);
}
