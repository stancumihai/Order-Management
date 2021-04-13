package org.stancuMihai.validator;


import org.stancuMihai.model.Client;

@FunctionalInterface
public interface AppValidation {

    void validate(Client client);
}
