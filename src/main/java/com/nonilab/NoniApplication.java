package com.nonilab;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static com.nonilab.util.KeyPairGeneratorUtil.generateKeyPair;

@SpringBootApplication
public class NoniApplication {

	public static void main(String[] args) throws Exception {
		generateKeyPair("src/main/resources/privateKey.pem", "src/main/resources/publicKey.pem");
		SpringApplication.run(NoniApplication.class, args);
	}

}
