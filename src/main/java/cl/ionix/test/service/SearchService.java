package cl.ionix.test.service;

import cl.ionix.test.exception.BadRequestException;
import cl.ionix.test.exception.EncryptionException;
import cl.ionix.test.model.SearchRequest;
import cl.ionix.test.model.SearchResponse;

public interface SearchService {
	public SearchResponse searchByRut(SearchRequest searchRequest) throws EncryptionException, BadRequestException;
}
