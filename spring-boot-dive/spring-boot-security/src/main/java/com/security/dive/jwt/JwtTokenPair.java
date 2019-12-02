package com.security.dive.jwt;

import lombok.Data;

import java.io.Serializable;

@Data
public class JwtTokenPair implements Serializable {

    private String accessToken;

    private String refreshToken;
}
