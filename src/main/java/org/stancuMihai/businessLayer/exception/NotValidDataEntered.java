package org.stancuMihai.businessLayer.exception;

/***
 * Class that contains the exception
 */
public class NotValidDataEntered extends RuntimeException{

    public NotValidDataEntered(String message) {
        super(message);
    }
}
