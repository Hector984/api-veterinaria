package com.veterinaria.api_veterinaria.security;

import javax.crypto.SecretKey;

import io.jsonwebtoken.Jwts;

public class TokenJwtConfig {

    public static final SecretKey SECRET_KEY = Jwts.SIG.HS256.key().build();

    public static final long EXPIRATION_TIME = 86400000; // 1 dia

    public static final String PREFIX_TOKEN = "Bearer ";

    public static final String HEADER_AUTHORIZATION = "Authorization";

    public static final String CONTENT_TYPE = "application/json";
}
