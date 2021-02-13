package cl.ionix.test.exception;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import cl.ionix.test.model.ErrorResponse;

@RestControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
	@Value("${cl.ionix.message.exception.invalidrutnumber}")
	String INVALID_RUT_NUMBER_EXCEPTION;
	@Value("${cl.ionix.message.exception.invalidrutformat}")
	String INVALID_RUT_FORMAT_EXCEPTION;
	@Value("${cl.ionix.message.exception.duplicatedentry}")
	String DUPLICATED_ENTRY_EXCEPTION;
	@Value("${cl.ionix.message.exception.badrequest}")
	String BAD_REQUEST;
	@Value("${cl.ionix.message.exception.notfound}")
	String NOT_FOUND;
	@Value("${cl.ionix.message.exception.nocontent}")
	String NO_CONTENT;
	
	@ExceptionHandler(InvalidRutNumberException.class)
	public final ResponseEntity<ErrorResponse> handleInvalidRutNumberException(InvalidRutNumberException ex, WebRequest request) {
		List<String> details = new ArrayList<>();
		details.add(ex.getLocalizedMessage());
		ErrorResponse error = new ErrorResponse(INVALID_RUT_NUMBER_EXCEPTION, details);
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(InvalidRutFormatException.class)
	public final ResponseEntity<ErrorResponse> handleInvalidRutFormatException(InvalidRutFormatException ex, WebRequest request) {
		List<String> details = new ArrayList<>();
		details.add(ex.getLocalizedMessage());
		ErrorResponse error = new ErrorResponse(INVALID_RUT_FORMAT_EXCEPTION, details);
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(BadRequestException.class)
	public final ResponseEntity<ErrorResponse> handleBadRequestException(BadRequestException ex, WebRequest request) {
		List<String> details = new ArrayList<>();
		details.add(ex.getLocalizedMessage());
		ErrorResponse error = new ErrorResponse(BAD_REQUEST, details);
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(NotFoundException.class)
	public final ResponseEntity<ErrorResponse> handleNotFoundException(NotFoundException ex, WebRequest request) {
		List<String> details = new ArrayList<>();
		details.add(ex.getLocalizedMessage());
		ErrorResponse error = new ErrorResponse(NOT_FOUND, details);
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(DuplicatedEntryException.class)
	public final ResponseEntity<ErrorResponse> handleDuplicatedEntryException(DuplicatedEntryException ex,
			WebRequest request) {
		List<String> details = new ArrayList<>();
		details.add(ex.getLocalizedMessage());
		ErrorResponse error = new ErrorResponse(DUPLICATED_ENTRY_EXCEPTION, details);
		return new ResponseEntity<>(error, HttpStatus.UNPROCESSABLE_ENTITY);
	}
	
	@ExceptionHandler(NoContentException.class)
	public final ResponseEntity<ErrorResponse> handleNoContentException(NoContentException ex,
			WebRequest request) {
		List<String> details = new ArrayList<>();
		details.add(ex.getLocalizedMessage());
		ErrorResponse error = new ErrorResponse(NO_CONTENT, details);
		return new ResponseEntity<>(error, HttpStatus.NO_CONTENT);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ErrorResponse error = new ErrorResponse(BAD_REQUEST, ex.getBindingResult().getFieldErrors().stream()
				.map(x -> x.getDefaultMessage()).collect(Collectors.toList()));
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);

	}
}
