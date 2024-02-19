package com.app.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Rollback;

import com.app.entities.Gender;
import com.app.entities.UserEntity;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
class UserEntityDaoTest {

    @Autowired
    private UserEntityDao userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    void testAddUsers() {
        List<UserEntity> users = List.of(
            new UserEntity(LocalDate.parse("1990-01-01"), "India", "yash@gmail.com", "Yash", Gender.MALE, "Pathak", "9855463210", passwordEncoder.encode("yash1234"), "Maharashtra", "yash_pathak", "123456", true),
            new UserEntity(LocalDate.parse("1998-02-27"), "India", "Meet@gmail.com", "Meet", Gender.MALE, "Vasani", "7776503212", passwordEncoder.encode("meet1234"), "Gujarat", "meet_vasani", "422010", true),
            new UserEntity(LocalDate.parse("1999-11-09"), "India", "jayesh@gmail.com", "Jayesh", Gender.MALE, "Suthar", "8876643212", passwordEncoder.encode("jayesh1234"), "Gujarat", "jayesh_suthar", "422009",true),
            new UserEntity(LocalDate.parse("1992-03-15"), "India", "Ravi@gmail.com", "ravi", Gender.MALE, "Kore", "8773443211", passwordEncoder.encode("ravi1234"), "MP", "ravi", "654321", true),
            new UserEntity(LocalDate.parse("1985-07-20"), "India", "smita@gmail.com", "Smita", Gender.FEMALE, "Kadam", "7876073212", passwordEncoder.encode("alice1234"), "Karnataka", "smita", "987654", true),
            new UserEntity(LocalDate.parse("1989-04-12"), "India", "Akshata@gmail.com", "Akshata", Gender.FEMALE, "Dubey", "7020643212", passwordEncoder.encode("akshata1234"), "MP", "akshata_dubey", "324059",true),
            new UserEntity(LocalDate.parse("1998-01-17"), "India", "rohan@gmail.com", "Rohan", Gender.MALE, "Patil", "9976643287", passwordEncoder.encode("rohan1234"), "Maharashtra", "rohan_patil", "567112", true),
            new UserEntity(LocalDate.parse("1993-07-02"), "India", "aashi@gmail.com", "Aashi", Gender.FEMALE, "Dixit", "8770643212", passwordEncoder.encode("aashi1234"), "Rajasthan", "aashi_dixit", "672079", true),
            new UserEntity(LocalDate.parse("1990-12-01"), "India", "Ajay@gmail.com", "Ajay", Gender.MALE, "Budhewar", "7906643255", passwordEncoder.encode("ajay1234"), "AP", "ajay_budhewar", "513069", true),
            new UserEntity(LocalDate.parse("1994-04-17"), "India", "rohit@gmail.com", "Rohit", Gender.MALE, "Gaikar", "7970523212", passwordEncoder.encode("rohit1234"), "Maharashtra", "rohit_gaikar", "567110", true)
        );

        // Save the users to the database
        List<UserEntity> savedUsers = userRepo.saveAll(users);

        // Assert that users are saved successfully
        assertEquals(users.size(), savedUsers.size());
    }
}
