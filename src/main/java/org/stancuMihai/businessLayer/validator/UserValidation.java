package org.stancuMihai.businessLayer.validator;

import org.stancuMihai.businessLayer.exception.NotValidDataEntered;
import org.stancuMihai.model.Client;

/***
 * validates user attributes
 */
public class UserValidation implements AppValidation {
    @Override
    public void validate(Client client) {
        if (!checkIfEmailIsCorrect(client.getEmail())) {
            throw new NotValidDataEntered("Email format is incorrect");
        }
        if (checkIfFieldsAreEmpty(client)) {
            throw new NotValidDataEntered("No field should be empty");
        }
    }

    public boolean checkIfEmailIsCorrect(String email) {
        return email.matches("[a-zA-Z0-9]+@[a-zA-Z]+.[a-z]+");
    }

    public boolean checkIfFieldsAreEmpty(Client client) {
        return client.getName().isBlank() || client.getAddress().isBlank() || client.getEmail().isBlank() || client.getAge().equals(0);
    }
}
