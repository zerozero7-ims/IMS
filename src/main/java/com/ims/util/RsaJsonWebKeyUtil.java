package com.ims.util;

import org.jose4j.jwk.RsaJsonWebKey;
import org.jose4j.jwk.RsaJwkGenerator;
import org.jose4j.lang.JoseException;

import java.util.UUID;

public class RsaJsonWebKeyUtil {
    public static RsaJsonWebKey rsaJsonWebKey = null;
    private  RsaJsonWebKeyUtil(){

    }
    public static RsaJsonWebKey getInstance(){
        // 生成一个RSA密钥对，用于签署和验证JWT，包装在JWK中
        if(rsaJsonWebKey == null){
            try{
                rsaJsonWebKey = RsaJwkGenerator.generateJwk(2048); //为保证足够安全，其加密的位数为2048

                String keyId = UUID.randomUUID().toString().replaceAll("-", "");
                rsaJsonWebKey.setKeyId(keyId);
                System.out.println("keyid"+keyId);
                //rsaJsonWebKey.setKeyId("jwt1"); // 给JWK一个关键ID（kid），这是礼貌的做法,必须保证唯一，比如使用UUID生成的长度至少32位的随机字符串，可以全为数字或数字+字母
            }catch(JoseException e){
                e.printStackTrace();
            }
        }
        return rsaJsonWebKey;
    }
}
