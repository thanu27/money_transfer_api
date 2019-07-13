package moneytransfer.service.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang3.StringUtils;

import io.javalin.Context;
import moneytransfer.controller.context.ApiContext;
import moneytransfer.exception.ApiException;
import moneytransfer.exception.InsufficientBalanceException;
import moneytransfer.model.Account;
import moneytransfer.model.Response;
import moneytransfer.model.Transaction;
import moneytransfer.service.ApiService;

public class ApiServiceImpl implements ApiService{
	
	private ApiContext apiContext;
	private static boolean creditFlag = false;
	private static boolean debitFlag = false;
		
	public ApiServiceImpl () {
	}
	
	@Override
	public void transferMoney (final Context context, final Optional<Account> senderAccount, final Optional<Account> receiverAccount, String transferAmount) {
			  
		if(StringUtils.isNotBlank(transferAmount)) {
			if(!org.apache.commons.lang3.math.NumberUtils.isParsable(transferAmount)) {
				throw new ApiException("Amount should be numeric");
			}
			else {
			if (senderAccount.get().getBalance().compareTo(new BigDecimal(transferAmount))<=0) {
	            throw new InsufficientBalanceException(senderAccount.get().getAccountNumber());
	        }
			}
			
		}
		else {
			throw new ApiException("Please provide transfer amount");
		}
		
		
	    Transaction transaction =  transact(senderAccount.get(), receiverAccount.get(), transferAmount);
		transferMoney(context,transaction);
	}
	@Override
	public Map<String, Optional<Account>> getSenderAccountMap()
	{	
		Map<String, Optional<Account>> senderMap = new ConcurrentHashMap<String, Optional<Account>>();
			senderMap.put("1111",Optional.ofNullable(new Account("1111","ABC",new BigDecimal("5000"))));
			senderMap.put("2222",Optional.ofNullable(new Account("2222","XYZ",new BigDecimal("1000"))));
			
		return senderMap;
	}
			  
	@Override
	  public Map<String, Optional<Account>> getReceiverAccountMap()
	  {	
		Map<String, Optional<Account>> receiverMap = new ConcurrentHashMap<String, Optional<Account>>();
		receiverMap.put("3333",Optional.ofNullable(new Account("3333","XXX",new BigDecimal("5000"))));
		receiverMap.put("4444",Optional.ofNullable(new Account("4444","ZZZ",new BigDecimal("1000"))));
		
		return receiverMap;
	  }
	
	private void transferMoney(final Context context, final Transaction transaction) {
		try {  
			transferMoney(transaction);
	      Response response = Response.builder()
	          .message("Amount transferred successfully")
	          .object(transaction)
	          .build();
	      context.status(201).json(response);
	    } catch (Exception exception) {
	      Response response = Response.builder()
	        .message(exception.getMessage())
	        .build();
	      context.status(500).json(response);
	  }
	}
	
	private Transaction transact(Account sender, Account receiver, String amount) {
	    return Transaction.builder()
	        .transactionId(UUID.randomUUID().toString())
	        .senderAccount(sender)
	        .receiverAccount(receiver)
	        .transactionAmount(new BigDecimal(amount))
	        .creationDateTime(LocalDateTime.now())
	        .build();
	  }
	
	
	 public void transferMoney(Transaction transaction) {
 		if(transaction.senderAccount().getAccountLock().tryLock()) {
        	try {
        		if(transaction.receiverAccount().getAccountLock().tryLock()) {
        			try {
        				debitFlag = transaction.senderAccount().withdrawAmount(transaction.transactionAmount());
        				creditFlag = transaction.receiverAccount().addAmount(transaction.transactionAmount());
        			}
        			finally {
        				transaction.receiverAccount().getAccountLock().unlock();
    	        	}
        		}
        	}
        	finally {
        		transaction.senderAccount().getAccountLock().unlock();
        	}		        	
        }

        if(!creditFlag && debitFlag) {
        	if(transaction.senderAccount().getAccountLock().tryLock()) {
        	try {
        		transaction.senderAccount().addAmount(transaction.transactionAmount());
        	}
        	finally {
        		transaction.senderAccount().getAccountLock().unlock();
        	}
        	}
        }
		    
		}		
}
