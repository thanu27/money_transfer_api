package moneytransfer.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;

public class Transaction {
		
	private String transactionId;
	private Account senderAccount;
	private Account receiverAccount;
	private BigDecimal transactionAmount;
	private LocalDateTime creationDateTime;
	private final transient AtomicBoolean isTransaction;
			
	private Transaction(final Builder builder) {
		
	    this.transactionId = builder.transactionId;
	    this.senderAccount = builder.senderAccount;
	    this.receiverAccount= builder.receiverAccount;
	    this.transactionAmount = builder.transactionAmount;
	    this.creationDateTime = builder.creationDateTime;
	    this.isTransaction = new AtomicBoolean(true);
	 }
	
	public static Builder builder() {
	    return new Builder();
	}
	
	public String transactionId() {
	    return transactionId;
	}
	
	public Account senderAccount() {
	    return senderAccount;
	}
	
	public Account receiverAccount() {
	    return receiverAccount;
	}
	
	public BigDecimal transactionAmount() {
	    return transactionAmount;
	}
	
	public LocalDateTime creationDateTime() {
	    return creationDateTime;
	}
	
	public AtomicBoolean isTransaction() {
	    return isTransaction;
	  }
	
	@Override public boolean equals(Object o) {
	    if (this == o) {
	      return true;
	    }

	    if (o == null || getClass() != o.getClass()) {
	      return false;
	    }

	    Transaction transaction = (Transaction) o;

	    return Objects.equals(transactionId, transaction.transactionId)
	        && Objects.equals(senderAccount, transaction.senderAccount)
	        && Objects.equals(receiverAccount, transaction.receiverAccount)
	        && Objects.equals(creationDateTime, transaction.creationDateTime);
	  }

	  @Override public int hashCode() {
	    return Objects.hash(transactionId, senderAccount, receiverAccount, creationDateTime);
	  }
	  
	  public static class Builder {
		private String transactionId;
		private Account senderAccount;
		private Account receiverAccount;
		private BigDecimal transactionAmount;
		private LocalDateTime creationDateTime;

	    private Builder() {
	    }

	    public Builder transactionId(final String transactionId) {
	      this.transactionId = transactionId;
	      return this;
	    }

	    public Builder senderAccount(final Account senderAccount) {
	      this.senderAccount = senderAccount;
	      return this;
	    }

	    public Builder receiverAccount(final Account receiverAccount) {
	      this.receiverAccount = receiverAccount;
	      return this;
	    }

	    public Builder transactionAmount(final BigDecimal transactionAmount) {
	      this.transactionAmount = transactionAmount;
	      return this;
	    }

	    public Builder creationDateTime(final LocalDateTime creationDateTime) {
	      this.creationDateTime = creationDateTime;
	      return this;
	    }

	    public Transaction build() {
	      return new Transaction(this);
	    }
	  }
	  
}
