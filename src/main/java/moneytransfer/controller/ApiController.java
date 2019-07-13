package moneytransfer.controller;

import java.util.Optional;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.apache.commons.lang3.StringUtils;

import io.javalin.Context;
import moneytransfer.controller.context.ApiContext;

import moneytransfer.exception.AccountNotExistsException;
import moneytransfer.exception.ApiException;
import moneytransfer.model.Account;
import moneytransfer.service.ApiService;
@Singleton
public class ApiController{
	
	private ApiService service;
	private ApiContext apiContext;

	@Inject
	public ApiController (ApiContext apiContext, ApiService service) {
		this.apiContext =apiContext;
		this.service = service;
	}
	
	public ApiController () {};
	
	public void transferMoney(final Context context) {
			String senderAccountNumber = context.formParam("senderAccountNumber");
		    String receiverAccountNumber = context.formParam("receiverAccountNumber");
		    String transferAmount = context.formParam("amount");	    
		    
		    if(StringUtils.isBlank(senderAccountNumber) || StringUtils.isBlank(receiverAccountNumber)) {
	    		throw new ApiException("Please provide valid account number");	
	    	}
	    	if(senderAccountNumber.equals(receiverAccountNumber)) {
	    		throw new ApiException("Sender and Receiver Account shouldn't be the same");	
		    }
   
			Optional<Account> senderAccount = service.getSenderAccountMap().get(senderAccountNumber);
		    Optional<Account> receiverAccount = service.getReceiverAccountMap().get(receiverAccountNumber);
		    
		    if(null==senderAccount || !senderAccount.isPresent()) {
		    	
	    	throw new AccountNotExistsException(senderAccountNumber);
		    }
		    
		    if(null==receiverAccount || !receiverAccount.isPresent()) {
	    	
		    	throw new AccountNotExistsException(receiverAccountNumber);
		    }	    
		    service.transferMoney(context, senderAccount, receiverAccount, transferAmount);
	}

}
