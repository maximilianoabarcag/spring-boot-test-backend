package cl.ionix.test.service.impl;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import cl.ionix.test.exception.EncryptionException;
import cl.ionix.test.service.EncryptionService;

@Service
public class EncryptionServiceImpl implements EncryptionService {

	@Value("${cl.ionix.encryption.key}")
	String ENCRYPTION_KEY;
	
	@Override
	public String encryptData(String data) throws EncryptionException {
		try {
			DESKeySpec desKeySpec = new DESKeySpec(ENCRYPTION_KEY.getBytes(StandardCharsets.UTF_8));
			SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("DES");
	        SecretKey secretKey = secretKeyFactory.generateSecret(desKeySpec);
	        byte[] bytes = data.getBytes(StandardCharsets.UTF_8);
	        Cipher cipher = Cipher.getInstance("DES");
	        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
	        return Base64.getEncoder().encodeToString(cipher.doFinal(bytes));

		} catch (Exception e) {
			throw new EncryptionException("Can't encrypt: " + e.getMessage()!= null ? e.getMessage() : "NO DETAIL");
		}
	}

}
