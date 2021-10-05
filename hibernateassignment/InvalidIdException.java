package org.te.hibernateassignment;

public class InvalidIdException extends Exception {
 String str;

public InvalidIdException(String str) {
	super();
	this.str = str;
}
public String getMessage() {
	return this.str;
}
 
}
