package moneytransfer.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import java.util.Objects;

public class Account {
   private transient Lock accountLock;
   private BigDecimal balance;
   private final String accountNumber;
   private final String accountHolder;
   private boolean debitFlag=false;
   private boolean creditFlag=false;

    public Account(String accountNumber, String accountHolder, BigDecimal balance) {
    	this.balance = balance;
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.accountLock = new ReentrantLock();
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public Lock getAccountLock() {
        return accountLock;
    }

    public BigDecimal getBalance() {
        this.accountLock.tryLock();
        try {
        	return this.balance.setScale(2, RoundingMode.HALF_DOWN);
        } finally {
            this.accountLock.unlock();
        }
    }

    public boolean addAmount(BigDecimal amount) { 
       this.balance = this.balance.add(amount);
       System.out.println("this.balance add:"+this.balance);
       creditFlag = true;
       return creditFlag;
    }
    
    public boolean withdrawAmount(BigDecimal amount) {
       this.balance = this.balance.subtract(amount);
       System.out.println("this.balance with:"+this.balance);
       debitFlag = true;
       return debitFlag;
    }
    
    @Override public boolean equals(Object o) {
        if (this == o) {
          return true;
        }

        if (o == null || getClass() != o.getClass()) {
          return false;
        }

        Account account = (Account) o;
        return Objects.equals(this.accountNumber, account.getAccountNumber())
            && Objects.equals(this.accountHolder, account.getAccountHolder());
      }

      @Override public int hashCode() {
        return Objects.hash(this.accountNumber, this.accountHolder);
      }

}
