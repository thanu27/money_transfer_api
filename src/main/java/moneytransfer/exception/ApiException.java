package moneytransfer.exception;

public class ApiException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	private final String message;

	public ApiException(final String message) {
	   this.message = message;
	}
	
	@Override 
	public String getMessage() {
	   return String.format(message);
	}

}
