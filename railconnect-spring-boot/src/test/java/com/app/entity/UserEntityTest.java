package com.app.entity;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Rollback;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;



import com.app.dao.UserEntityDao;
import com.app.entities.UserEntity;
import com.app.entities.UserEntity.Gender;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE) // Use actual database for testing
@Rollback(false) // Caution: Changes persist in actual database
class UserEntityDaoTest {

    @Autowired
    private UserEntityDao userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    void testCreateUserWithValidData() {
        // Valid user data
        String firstName = "Jane";
        String lastName = "Doe";
        LocalDate birthDate = LocalDate.of(1990, 1, 1);
        Gender gender = Gender.FEMALE;
        String mobileNumber = "123-456-7890";
        String email = "jane.doe@example.com";
        String username = "janedoe";
        String rawPassword = "securePassword123"; // Avoid hardcoding in real tests
        String country = "USA";
        String state = "California";
        String zip = "90210";
        boolean isActive = true;

        // Encrypted password
        String encodedPassword = passwordEncoder.encode(rawPassword);

        // Create and save user
        UserEntity user = new UserEntity(null, firstName, lastName, birthDate, gender, mobileNumber, email, username, encodedPassword, country, state, zip, isActive);
        UserEntity savedUser = userRepo.save(user);

        // Assertions
        assertNotNull(savedUser);
        assertEquals(firstName, savedUser.getFirstName());
        assertEquals(lastName, savedUser.getLastName());
        assertEquals(birthDate, savedUser.getBirthDate());
        assertEquals(gender, savedUser.getGender());
        assertEquals(mobileNumber, savedUser.getMobileNumber());
        assertEquals(email, savedUser.getEmail());
        assertEquals(username, savedUser.getUsername());
        // Don't assert password for security reasons
        assertEquals(country, savedUser.getCountry());
        assertEquals(state, savedUser.getState());
        assertEquals(zip, savedUser.getZip());
        assertEquals(isActive, savedUser.isActive());
    }


    // ... Add more test cases for invalid data, edge cases, validation rules, etc.
}
