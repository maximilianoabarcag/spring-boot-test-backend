package cl.ionix.test.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class UserResponse {
	@Getter 
	@Setter
	int userId;
	@Getter 
	@Setter
	String name;
	@Getter 
	@Setter
	String userName;
	@Getter 
	@Setter
	String email;
	@Getter 
	@Setter
	String phone;
}
