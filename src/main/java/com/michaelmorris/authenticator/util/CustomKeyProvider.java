package com.michaelmorris.authenticator.util;

import com.michaelmorris.authenticator.config.KeysProperties;
import org.springframework.beans.factory.annotation.Autowired;
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
public class CustomKeyProvider implements KeyProvider {

    private final KeysProperties keysProperties;

    private static RSAPrivateKey privateKey = null;
    private static RSAPublicKey publicKey = null;

    @Autowired
    public CustomKeyProvider(KeysProperties keysProperties) {
        this.keysProperties = keysProperties;
    }

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

    private boolean attemptToReadKeys() throws Exception {
        KeyFactory keyFactory = KeyFactory.getInstance(this.keysProperties.algorithm);
        File privateKeyFile = new File(this.keysProperties.privateKeyPath);
        File publicKeyFile = new File(this.keysProperties.publicKeyPath);

        if (!privateKeyFile.exists() || !publicKeyFile.exists()) {
            return false;
        }

        privateKey = (RSAPrivateKey) keyFactory.generatePrivate(new PKCS8EncodedKeySpec(Files.readAllBytes(privateKeyFile.toPath())));
        publicKey = (RSAPublicKey) keyFactory.generatePublic(new X509EncodedKeySpec(Files.readAllBytes(publicKeyFile.toPath())));

        return true;
    }

    private void generateKeys() throws Exception {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(this.keysProperties.algorithm);
        keyPairGenerator.initialize(this.keysProperties.keySize, SecureRandom.getInstanceStrong());
        KeyPair pair = keyPairGenerator.generateKeyPair();

        privateKey = (RSAPrivateKey) pair.getPrivate();
        publicKey = (RSAPublicKey) pair.getPublic();

        try (var privateOutputStream = new FileOutputStream(this.keysProperties.privateKeyPath)) {
            privateOutputStream.write(privateKey.getEncoded());
        }
        try (var publicOutputStream = new FileOutputStream(this.keysProperties.publicKeyPath)) {
            publicOutputStream.write(publicKey.getEncoded());
        }
    }

}
