package cl.ionix.test.controller;

import java.security.GeneralSecurityException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.ionix.test.domain.EndPoint;
import cl.ionix.test.exception.BadRequestException;
import cl.ionix.test.exception.DuplicatedEntryException;
import cl.ionix.test.exception.EncryptionException;
import cl.ionix.test.exception.InvalidRutFormatException;
import cl.ionix.test.exception.InvalidRutNumberException;
import cl.ionix.test.model.SearchRequest;
import cl.ionix.test.model.SearchResponse;
import cl.ionix.test.service.SearchService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "Search")
@RestController
@RequestMapping(EndPoint.VERSION_1 + "/search")
public class SearchController {

	@Autowired
	SearchService searchService;

	@ApiOperation(value = "search rut data", notes = "search data by rut on a external api")
	@ApiResponses(value = { @ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 404, message = "Not Found"), @ApiResponse(code = 406, message = "Not Acceptable"),
			@ApiResponse(code = 422, message = "Unprocessable Entity"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SearchResponse> searchByRut(@RequestBody @Valid SearchRequest searchRequest)
			throws GeneralSecurityException, EncryptionException, BadRequestException {
		return new ResponseEntity<>(searchService.searchByRut(searchRequest), HttpStatus.OK);
	}
}
