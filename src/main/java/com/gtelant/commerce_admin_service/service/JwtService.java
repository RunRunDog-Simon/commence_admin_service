package com.gtelant.commerce_admin_service.service;

import com.gtelant.commerce_admin_service.models.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
public class JwtService {
    //第三部 負責產生、解析 Json web token
    //本次練習採用EMAIL(unique)作為登入

    private Key getKey(){
        byte[] keyByte = Decoders.BASE64.decode("YXJyYW5nZWd1YXJkYXBhcnRmdWxseWxlYWZoYWR0cnVja2VtcHR5aGFwcGlseWJsb2M=");
        return Keys.hmacShaKeyFor(keyByte);
    }

    //從JWT中解析出Email
    public String getEmailFromToken(String jwtToken) {
        return Jwts.parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(jwtToken)
                .getBody()
                .getSubject();
    }

    public String generateToken(User user){
        return Jwts.builder()
                .setSubject(user.getEmail())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 2880000)) //0.8小時候過期
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();
    }
}
