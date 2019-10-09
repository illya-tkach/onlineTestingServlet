package org.itstep.model.entity.validator;

import org.itstep.model.entity.UserAccount;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class UserValidatorTest {
    private UserValidator userValidator;

    @Before
    public void setUp() throws Exception {
        userValidator = new UserValidator();
    }

    @Test
    public void testValidate_valid() {
        UserAccount user = UserAccount.builder()
                .email("username@gmail.com")
                .password("password")
                .firstName("Ivan")
                .lastName("Franko")
                .build();
        userValidator.validate(user);

        assertFalse(userValidator.hasErrors());
    }

    @Test
    public void testValidate_inValidUserName() {
        UserAccount user = UserAccount.builder()
                .email("")
                .password("password")
                .firstName("Ivan")
                .lastName("Franko")
                .build();
        userValidator.validate(user);

        assertTrue(userValidator.hasErrors());
    }


    @Test
    public void testValidate_invalidPassword() {
        UserAccount user = UserAccount.builder()
                .email("")
                .password("")
                .firstName("Ivan")
                .lastName("Franko")
                .build();
        userValidator.validate(user);

        assertTrue(userValidator.hasErrors());
    }
}
