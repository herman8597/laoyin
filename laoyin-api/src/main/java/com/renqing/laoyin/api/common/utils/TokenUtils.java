package com.bat.laoyin.api.common.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

/**
 * @author: lim
 * @description: TODO
 * @date: 2021/8/7 16:41
 */
public class TokenUtils {

    /**
     * 静态类加载时 生成Key
     */
    public static final SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    /**
     * 校验是不是jwt签名
     * 
     * @param token
     * @return
     */
    public static boolean verify(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException | SignatureException
            | IllegalArgumentException e) {
            e.printStackTrace();
            return false;
        }

    }

    public static void main(String[] args) throws Exception {
        Map<String, Object> claims = new HashMap<>();
        claims.put("uid", "DSSFAWDWADAS...");
        claims.put("user_name", "admin");
        claims.put("nick_name", "DASDA121");
        String ab = TokenUtils.createJWT(claims);
        System.out.println(ab);
        Claims claims1 = TokenUtils.parseJWT(ab);
        System.out.println(claims1);
    }

    /**
     * 创建jwt
     *
     * @param claims
     * @return
     * @throws Exception
     */
    public static String createJWT(Map<String, Object> claims) {
        Calendar instance = Calendar.getInstance();
        Date issuedAt = instance.getTime();
        instance.add(Calendar.HOUR, 2);
        Date expiration = instance.getTime();
        return Jwts.builder().setClaims(claims).setIssuedAt(issuedAt).setExpiration(expiration).signWith(key).compact();
    }

    /**
     * 解密jwt
     *
     * @param jwt
     * @return
     * @throws Exception
     */
    public static Claims parseJWT(String jwt) {
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwt).getBody();
    }
}
