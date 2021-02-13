package cl.ionix.test.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor
@Entity
@NamedQuery(name = "User.findByUserName", query = "SELECT r FROM User r WHERE r.userName = :userName")
@NamedQuery(name = "User.findByEmail", query = "SELECT r FROM User r WHERE r.email = :email")
public class User {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
