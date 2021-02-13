package cl.ionix.test.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor
public class UserRequest {
	@Getter 
	@Setter
	@NotNull(message = "Please provide a 'name'")
	@Size(min = 1, max = 50, message = "The length of the field 'name' must be between 1 and 50")
	String name;
	@Getter 
	@Setter
	@NotNull(message = "Please provide a 'userName'")
	@Size(min = 1, max = 30, message = "The length of the field 'userName' must be between 1 and 30")
	String userName;
	@Getter 
	@Setter
	@Size(min = 1, max = 255, message = "The length of the field 'email' must be between 1 and 255")
	@Pattern(regexp=".+@.+\\..+", message="Please provide a valid email address")
	@NotNull(message = "Please provide an 'email'")
	String email;
	@Getter 
	@Setter
	@Size(min = 1, max = 15, message = "The length of the field 'phone' must be between 1 and 15")
	@Pattern(regexp="^([0-9])+$", message="Please provide a valid phone number (only numbers between 0 and 9")
	String phone;
}
