package cl.ionix.test.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;

import cl.ionix.test.exception.BadRequestException;
import cl.ionix.test.exception.EncryptionException;
import cl.ionix.test.exception.InvalidRutFormatException;
import cl.ionix.test.exception.InvalidRutNumberException;
import cl.ionix.test.model.Result;
import cl.ionix.test.model.SearchRequest;
import cl.ionix.test.model.SearchResponse;
import cl.ionix.test.service.EncryptionService;
import cl.ionix.test.service.SearchService;
import cl.ionix.test.util.RutNumber;
import net.minidev.json.JSONArray;

@Service
public class SearchServiceImpl implements SearchService {

	@Autowired
	EncryptionService encryptionService;

	@Value("${cl.ionix.external-services.search}")
	String SEARCH_SERVICE_URL;

	@Override
	public SearchResponse searchByRut(SearchRequest searchRequest) throws EncryptionException, BadRequestException {
		try {
			RutNumber.isValid(searchRequest.getRut());
		} catch (InvalidRutFormatException | InvalidRutNumberException e) {
			throw new BadRequestException(e.getMessage());
		}

		
		long start = new Date().getTime();
		String base64String = encryptionService.encryptData(searchRequest.getRut());
		
		SearchResponse response = new SearchResponse();

		StringBuilder url = new StringBuilder();
		url.append(SEARCH_SERVICE_URL);
		url.append(base64String);

		RestTemplate restTemplate = new RestTemplate();
		String json = restTemplate.getForObject(url.toString(), String.class);
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			JsonNode jsonNode = objectMapper.readTree(json);
			response.setDescription(jsonNode.get("description").asText());
			response.setResponseCode(jsonNode.get("responseCode").asInt());
			
			JSONArray jsonArray = JsonPath.read(json, "$.result.items[*]");

			Result result = new Result();
			result.setRegisterCount(jsonArray.size());
			response.setResult(result);
		} catch (Exception e) {

		}
		
		long end = new Date().getTime();
		long elapsed = end - start;
		
		response.setElapsedTime(elapsed);

		return response;
	}

}
