package com.app.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Rollback;

import com.app.entities.UserEntity;


@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
class UserEntityDaoTest {
	// dep
	@Autowired
	private UserEntityDao userRepo;

	@Autowired
	private PasswordEncoder enc;

	/* ERROROUS CODE
	@Test
	void testAddUsers() {
		List<UserEntity> list = List.of(
				new UserEntity(null ,"mukesh", "rathod", LocalDate.parse("2001-04-01"), "male", "9876543215",
						"m@gmail.com", "mukesh18", "12345678", "India", "Gujarat", "Vapi", true)); 
				
		List<UserEntity> list2 = userRepo.saveAll(list);
		assertEquals(1, list2.size());

	}
	*/

}
