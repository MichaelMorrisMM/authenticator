package com.michaelmorris.authenticator.auth;

import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

@Component
public class AuthKeyProvider implements KeyProvider {

    private static final String PATH_PRIVATE_KEY = "keys.key";
    private static final String PATH_PUBLIC_KEY = "keys.pub";
    private static final String ALGORITHM = "RSA";
    private static final int KEY_SIZE = 2048;

    private static RSAPrivateKey privateKey = null;
    private static RSAPublicKey publicKey = null;

    @Override
    public RSAPublicKey getPublicKeyById(String s) {
        try {
            if (publicKey == null && !attemptToReadKeys()) {
                generateKeys();
            }
            return publicKey;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public RSAPrivateKey getPrivateKey() {
        try {
            if (privateKey == null && !attemptToReadKeys()) {
                generateKeys();
            }
            return privateKey;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public String getPrivateKeyId() {
        return null;
    }

    public String getPublicKeyEncodedAsText() {
        RSAPublicKey key = this.getPublicKeyById(null);
        if (key == null) {
            return "";
        }
        return "-----BEGIN RSA PUBLIC KEY-----\n" + Base64.getEncoder().encodeToString(key.getEncoded()) + "\n-----END RSA PUBLIC KEY-----\n";
    }

    private static boolean attemptToReadKeys() throws Exception {
        KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
        File privateKeyFile = new File(PATH_PRIVATE_KEY);
        File publicKeyFile = new File(PATH_PUBLIC_KEY);

        if (!privateKeyFile.exists() || !publicKeyFile.exists()) {
            return false;
        }

        privateKey = (RSAPrivateKey) keyFactory.generatePrivate(new PKCS8EncodedKeySpec(Files.readAllBytes(privateKeyFile.toPath())));
        publicKey = (RSAPublicKey) keyFactory.generatePublic(new X509EncodedKeySpec(Files.readAllBytes(publicKeyFile.toPath())));

        return true;
    }

    private static void generateKeys() throws Exception {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(ALGORITHM);
        keyPairGenerator.initialize(KEY_SIZE, SecureRandom.getInstanceStrong());
        KeyPair pair = keyPairGenerator.generateKeyPair();

        privateKey = (RSAPrivateKey) pair.getPrivate();
        publicKey = (RSAPublicKey) pair.getPublic();

        try (var privateOutputStream = new FileOutputStream(PATH_PRIVATE_KEY)) {
            privateOutputStream.write(privateKey.getEncoded());
        }
        try (var publicOutputStream = new FileOutputStream(PATH_PUBLIC_KEY)) {
            publicOutputStream.write(publicKey.getEncoded());
        }
    }

}
