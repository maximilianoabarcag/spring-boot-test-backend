package cl.ionix.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.ionix.test.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	public User findByUserName(String userName);
	public User findByEmail(String email);
}
