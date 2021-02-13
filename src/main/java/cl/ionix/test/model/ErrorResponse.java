package cl.ionix.test.model;

import java.util.List;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor
public class ErrorResponse {
	@Getter 
	@Setter
	String message;
	@Getter 
	@Setter
	List<String> details;
}
