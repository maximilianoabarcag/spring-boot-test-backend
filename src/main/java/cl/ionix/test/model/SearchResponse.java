package cl.ionix.test.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor
public class SearchResponse {
	@Getter 
	@Setter
	int responseCode;
	@Getter 
	@Setter
	String description;
	@Getter 
	@Setter
	long elapsedTime;
	@Getter 
	@Setter
	Result result;
}
