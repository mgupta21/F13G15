package org.java.app.exceptions;

public class DuplicateUserException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public DuplicateUserException(String message) {
        super(message); 
    }
	
	
}
