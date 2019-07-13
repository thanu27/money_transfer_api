package moneytransfer.exception;

public class InsufficientBalanceException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	private final String account;

	public InsufficientBalanceException(final String account) {
	   this.account = account;
	}

	@Override public String getMessage() {
	   return String.format("Account %s is not having sufficient balance", account);
	}

}
