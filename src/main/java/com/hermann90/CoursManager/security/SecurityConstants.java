/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hermann90.CoursManager.security;

/**
 *This Class contains all constants using to perform security (token)
 * 
 * @author hermann90
 */
public class SecurityConstants {
    public static final String SECRET = "hermannchefouet@gmail.com";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final long  EXPIRATION_TIME = 864_000_000; // 10 days 
}
