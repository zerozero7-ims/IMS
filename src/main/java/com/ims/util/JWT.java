package com.ims.util;

import com.auth0.jwt.JWTSigner;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.internal.com.fasterxml.jackson.databind.ObjectMapper;
import com.ims.entity.Login;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * JSON Web令牌是一种紧凑的url安全方法，表示在两方之间传输的声明/属性。 这个例子演示了生产和消费一个签名的JWT
 */
public class JWT {
    private static final String SECRET="RNYBYNESJWTSECRET594A4AJSONZXCVBNM";//密钥
    private static final String EXP = "exp";  //有效时间
    private static final String PAYLOAD = "payload"; //载荷
    //加密
    public static <T> String sign(T object, long maxAge){
        try{
            final JWTSigner signer = new JWTSigner(SECRET);
            final Map<String, Object> claims = new HashMap<String, Object>();
            ObjectMapper mapper = new ObjectMapper();
            String jsonString = mapper.writeValueAsString(object);
            claims.put(PAYLOAD, jsonString);
            claims.put(EXP, System.currentTimeMillis() + maxAge);
            return signer.sign(claims);
        } catch (Exception e){
            return null;
        }
    }
    //解密
    public static <T> T unsign(String jwt, Class<T> classT){
        final JWTVerifier verifier = new JWTVerifier(SECRET);
        try{
            final Map<String, Object> claims = verifier.verify(jwt);
            if(claims.containsKey(EXP) && claims.containsKey(PAYLOAD)){
                long exp = (Long) claims.get(EXP);
                long currentTimeMillis = System.currentTimeMillis();
                if(exp>currentTimeMillis){
                    String json = (String) claims.get(PAYLOAD);
                    ObjectMapper objectMapper = new ObjectMapper();
                    return objectMapper.readValue(json, classT);
                }
            }
            return null;
        } catch (Exception e){
            return null;
        }
    }
}
