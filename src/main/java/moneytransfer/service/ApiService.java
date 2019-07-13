package moneytransfer.service;

import java.util.Map;
import java.util.Optional;
import io.javalin.Context;
import moneytransfer.model.Account;

public interface ApiService {		
	void transferMoney (final Context context, final Optional<Account> senderAccount, final Optional<Account> receiverAccount, String transferAmount) ;
	Map<String, Optional<Account>> getSenderAccountMap();
	Map<String, Optional<Account>> getReceiverAccountMap();
}
