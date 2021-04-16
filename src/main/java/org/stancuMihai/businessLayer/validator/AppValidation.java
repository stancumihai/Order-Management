package org.stancuMihai.businessLayer.validator;


import org.stancuMihai.model.Client;

/***
 * Interface for validation
 */
@FunctionalInterface
public interface AppValidation {

    void validate(Client client);
}
