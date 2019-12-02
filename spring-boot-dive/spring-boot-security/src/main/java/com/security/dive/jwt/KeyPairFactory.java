package com.security.dive.jwt;


import org.springframework.core.io.ClassPathResource;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyStore;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateCrtKey;
import java.security.spec.RSAPublicKeySpec;

class KeyPairFactory {

     private KeyStore store;

     private final Object lock = new Object();

     /**
      * 获取公私钥.
      *
      * @param keyPath  jks 文件在 resources 下的classpath
      * @param keyAlias  keytool 生成的 -alias 值  felordcn
      * @param keyPass  keytool 生成的  -storepass 值  123456
      * @return the key pair 公私钥对
      */
     KeyPair create(String keyPath, String keyAlias, String keyPass) {
         ClassPathResource resource = new ClassPathResource(keyPath);
         char[] pem = keyPass.toCharArray();
         try {
             synchronized (lock) {
                 if (store == null) {
                     synchronized (lock) {
                         store = KeyStore.getInstance("jks");
                         store.load(resource.getInputStream(), pem);
                     }
                 }
             }
             RSAPrivateCrtKey key = (RSAPrivateCrtKey) store.getKey(keyAlias, pem);
             RSAPublicKeySpec spec = new RSAPublicKeySpec(key.getModulus(), key.getPublicExponent());
             PublicKey publicKey = KeyFactory.getInstance("RSA").generatePublic(spec);
             return new KeyPair(publicKey, key);
         } catch (Exception e) {
             throw new IllegalStateException("Cannot load keys from store: " + resource, e);
         }

     }

}
