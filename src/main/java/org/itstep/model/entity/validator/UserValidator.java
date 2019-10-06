package org.itstep.model.entity.validator;

import org.itstep.model.entity.UserAccount;

public class UserValidator extends EntityValidator<UserAccount> {

    @Override
    public void validate(UserAccount userAccount) {
        if (userAccount.getEmail().trim().isEmpty()) {
            addError("emailError", "Email cannot be empty");
        }

        if (userAccount.getPassword().trim().isEmpty()) {
            addError("passwordError", "Password cannot be empty");
        }

        validateWithRegex(userAccount.getEmail(), REGEX_EMAIL, "emailError", "Your email is't correct");
    }
}
