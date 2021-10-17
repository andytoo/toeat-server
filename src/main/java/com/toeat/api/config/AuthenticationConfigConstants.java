package com.toeat.api.config;

public class AuthenticationConfigConstants {
    public static final String API_SECRET_KEY = "toeatapisecretkey";//TODO how to store it securely
    public static final long ACCESS_TOKEN_VALIDITY =  10 * 60 * 1000;//TODO 10 minutes
    public static final long REFRESH_TOKEN_VALIDITY = 1 * 60 * 60 * 1000;//TODO 1 HOUR
}
