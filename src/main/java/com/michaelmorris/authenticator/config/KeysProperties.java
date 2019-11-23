package com.michaelmorris.authenticator.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties("keys")
@Component
@Getter
@Setter
public class KeysProperties {

    public String privateKeyPath;
    public String publicKeyPath;
    public String algorithm;
    public int keySize;

}
