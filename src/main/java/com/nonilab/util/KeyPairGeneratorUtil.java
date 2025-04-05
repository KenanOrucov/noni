package com.nonilab.util;

import java.io.FileWriter;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.util.Base64;

public class KeyPairGeneratorUtil {

    public static void generateKeyPair(String privateKeyPath, String publicKeyPath) throws Exception {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();

        try (FileWriter privateKeyWriter = new FileWriter(privateKeyPath)) {
            privateKeyWriter.write(Base64.getEncoder().encodeToString(keyPair.getPrivate().getEncoded()));
        }

        try (FileWriter publicKeyWriter = new FileWriter(publicKeyPath)) {
            publicKeyWriter.write(Base64.getEncoder().encodeToString(keyPair.getPublic().getEncoded()));
        }
    }

}
