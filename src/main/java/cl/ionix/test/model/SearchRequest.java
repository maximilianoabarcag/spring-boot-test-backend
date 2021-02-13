package cl.ionix.test.model;

import javax.validation.constraints.NotNull;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor
public class SearchRequest {
	@Getter 
	@Setter
	@NotNull(message = "Please provide a 'rut'")
	String rut;
}
