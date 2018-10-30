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
    private static final String SECRET="RNYBYNESJWTSECRET594A4AJSONZXCVBNM";
    private static final String EXP = "exp";
    private static final String PAYLOAD = "payload";


    public static void main(String[] args) throws Exception{
        Login login = new Login();
        login.setUid(2);
        login.setUsername("abc");
        login.setPassword("1234");
        String token = JWT.sign(login,60L* 1000L* 30L);
        System.out.println("----token:"+token);

        String token1 = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE1NDA0NTA1NDg4NzMsInBheWxvYWQiOiJ7XCJ1aWRcIjoyLFwidXNlcm5hbWVcIjpcImFiY1wiLFwicGFzc3dvcmRcIjpcIjEyMzRcIn0ifQ.12kSDETm1FBFWrNX7wDB52EIBcnsh2gF1Riaf5czWs0";
        String token0 = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE1NDA0NTA0MTQ2MTMsInBheWxvYWQiOiJ7XCJ1aWRcIjoyLFwidXNlcm5hbWVcIjpcImFiY1wiLFwicGFzc3dvcmRcIjpcIjEyMzRcIn0ifQ.iWg0nUV_8cyUb9SSsM4GmF6oQUZ_LgST3PPuIn4Nx7s";
        String token2 = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE1NDAzNzExNzY1MjIsInBheWxvYWQiOiJ7XCJ1aWRcIjoyMzUsXCJ1c2VybmFtZVwiOlwiYWJjZFwiLFwicGFzc3dvcmRcIjpcIjEyMzRcIn0ifQ.fL17Qyxsv8Ck6C2ctEaWAoDZQJJNcgfmWVgm18q6Xec";
        String token3 = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE1NDAzNzEyMjQ1MTYsInBheWxvYWQiOiJ7XCJ1aWRcIjoyMzQsXCJ1c2VybmFtZVwiOlwiYWJjZGVmXCIsXCJwYXNzd29yZFwiOlwiMTIzNFwifSJ9.0k79b7NuxT8YU3-XwxlErvF1ZFfmUCriHVP_2xL32gk";
        String token4 = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE1NDAzNzEyNjMxMjYsInBheWxvYWQiOiJ7XCJ1aWRcIjoyMzQsXCJ1c2VybmFtZVwiOlwiYWJjZFwiLFwicGFzc3dvcmRcIjpcIjEyMzQ1XCJ9In0.-o7eS-H2_5pRF0ts11K7qtPbxoLtOkOCAsL_VelENGQ";
        String token5 = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE1NDAzNzEzNzA3MTgsInBheWxvYWQiOiJ7XCJ1aWRcIjoyMzQsXCJ1c2VybmFtZVwiOlwiYWJjZFwiLFwicGFzc3dvcmRcIjpcIjEyMzQ1XCJ9In0.o6Nw1NaXWacz_g6XqwR9FR5jEFMrneATCJg4DSBrCWw";
        System.out.println("-----解密---------");
        Login l = JWT.unsign(token, Login.class);
        System.out.println("payload-id:"+l.getUid());
        System.out.println("payload-username:"+l.getUsername());
        System.out.println("payload-password:"+l.getPassword());
        System.out.println("payload:"+l);



    }
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
