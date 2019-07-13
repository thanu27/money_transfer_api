package moneytransfer.exception;

public class AccountNotExistsException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	private final String account;

	public AccountNotExistsException(final String account) {
	   this.account = account;
	}

	@Override public String getMessage() {
	   return String.format("Account %s does not exist", account);
	}

}
