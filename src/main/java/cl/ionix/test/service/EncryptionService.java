package cl.ionix.test.service;

import cl.ionix.test.exception.EncryptionException;

public interface EncryptionService {
	public String encryptData(String data) throws EncryptionException;
}
